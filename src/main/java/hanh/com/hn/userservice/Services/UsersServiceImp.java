package hanh.com.hn.userservice.Services;

import hanh.com.hn.userservice.model.users;
import hanh.com.hn.userservice.repo.usersRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UsersServiceImp {

        @Autowired
        private static usersRepository repository;

        public static Page<users> _getAllUsers() {

            System.out.println("-------------"+ (Page<users>) repository.findAll());
            return (Page<users>) repository.findAll();
        }

        public users _getUserById(ObjectId id) {
            return repository.findBy_id(id);
        }

        public void _modifyUserById(  users users) {
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
