package hanh.com.hn.userservice.controller;
import com.sun.xml.internal.ws.handler.HandlerException;
import hanh.com.hn.userservice.UsersUtil.PasswordValidation;
import hanh.com.hn.userservice.Services.UsersServiceImp;
import hanh.com.hn.userservice.model.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersServiceImp usersServiceImp;
    @Autowired
    private PasswordValidation passwordValidation;

//    @GetMapping("/")
//    //** get all users
//    public List<users> getAllUsers() {
//        //System.out.println(repository.findAll());
//        return usersServiceImp._getAllUsers();
//    }
    @GetMapping(value= "/page/", produces = MediaType.APPLICATION_JSON_VALUE)
    //@GetMapping( value = "/page",params = { "page", "size" })
    @ResponseBody
    public ResponseEntity < PagedResources < users >> AllProducts(Pageable pageable, PagedResourcesAssembler assembler) {
        Page < users > products = UsersServiceImp._getAllUsers();
        PagedResources < users > pr = assembler.toResource(products, linkTo(this.getClass()).slash("/products").withSelfRel());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Link", createLinkHeader(pr));
        return new ResponseEntity < > (assembler.toResource(products, linkTo(this.getClass()).slash("/products").withSelfRel()), responseHeaders, HttpStatus.OK);
    }

    private String createLinkHeader(PagedResources < users > pr) {
        final StringBuilder linkHeader = new StringBuilder();
        linkHeader.append(buildLinkHeader(pr.getLinks("first").get(0).getHref(), "first"));
        linkHeader.append(", ");
        linkHeader.append(buildLinkHeader(pr.getLinks("next").get(0).getHref(), "next"));
        return linkHeader.toString();
    }

    public static String buildLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }
    @GetMapping("/{id}")
    //** get one user
    public users getUserById(@PathVariable("id") ObjectId id) {
        return usersServiceImp._getUserById(id);
    }

    @PutMapping("/{id}")
    //** update an existing user
    public void modifyUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody users users) {
        //** validate password
        if (passwordValidation.isValid(users.getPassword())) {
            users.set_id(id);
            usersServiceImp._modifyUserById(users);
        }
        else
        {
            throw new HandlerException("Invalid password");
        }
    }

    @PostMapping("/")
    //** Create an new user
    public users createUser(@Valid @RequestBody users users) {
        users.set_id(ObjectId.get());
        if (passwordValidation.isValid(users.getPassword()))
        {
            usersServiceImp._createUser(users);
            return users;}
        else
        {
            throw new HandlerException("Invalid password");
        }

    }

    @DeleteMapping("/{id}")
    //** Delete an user
    public void deleteUser(@PathVariable ObjectId id)
    {
        usersServiceImp._deleteUser(id);
    }
}