package hanh.com.hn.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;

public class users {
    @Id
        public ObjectId _id;

        @NotNull
        @NotBlank
        @Pattern(regexp = "^\\w+\\@\\w+\\.\\w+", message = "wrong email format")
        public String username;

        @NotNull
        @NotBlank
        @Size(min=8,message= "not enough character")
        public String password;
       // public int usid;

        // Constructors
        public users() {}

        public users(ObjectId _id, String username, String password) {
            this._id = _id;
            this.username = username;
            this.password = password;
        }

        // ObjectId needs to be converted to string
        public String get_id() { return _id.toHexString(); }
        public void set_id(ObjectId _id) { this._id = _id; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        //public int getUsid() { return usid; }
        //public void setUsid(int usid) { this.usid = usid; }
    }

