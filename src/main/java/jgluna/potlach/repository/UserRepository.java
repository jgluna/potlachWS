package jgluna.potlach.repository;

import jgluna.potlach.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String> {

    User save(User user);

    User findOne(String email);

//    ArrayList<User> queryTopUsers(int limit);
}
