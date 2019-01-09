package hanh.com.hn.userservice.Services;

import com.sun.xml.internal.ws.handler.HandlerException;
import hanh.com.hn.userservice.model.users;
import hanh.com.hn.userservice.repo.usersRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserServiceImp {

        @Autowired
        private usersRepository repository;

        public List<users> _getAllUsers() {
            return repository.findAll();
        }

        public users _getUserById(ObjectId id) {
            return repository.findBy_id(id);
        }

        public void _modifyUserById( ObjectId id, users users) {
            //** validate password
                repository.save(users);
        }

        public users _createUser(users users) {
            users.set_id(ObjectId.get());
                repository.save(users);
                return users;}

        public void _deleteUser(ObjectId id) {
            repository.delete(repository.findBy_id(id));
        }
    }
