package hanh.com.hn.userservice.repo;

import hanh.com.hn.userservice.model.users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface usersRepository extends MongoRepository<users, String> {
    users findBy_id(ObjectId _id);
}
