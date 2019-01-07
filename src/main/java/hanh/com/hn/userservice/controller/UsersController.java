package hanh.com.hn.userservice.controller;
import hanh.com.hn.userservice.model.users;
import hanh.com.hn.userservice.repo.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private usersRepository repository;

    @GetMapping("/")
    //** get all users
    public List<users> getAllUsers() {
        //System.out.println(repository.findAll());
        return repository.findAll();
    }

    @GetMapping("/{id}")
    //** get one user
    public users getUserById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @PutMapping("/{id}")
    //** update an existing user
    public void modifyUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody users users) {
        users.set_id(id);
        repository.save(users);
    }

    @PostMapping("/")
    //** Create an new user
    public users createUser(@Valid @RequestBody users users) {
        users.set_id(ObjectId.get());
        repository.save(users);
        return users;
    }

    @DeleteMapping("/{id}")
    //** Delete an user
    public void deleteUser(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}