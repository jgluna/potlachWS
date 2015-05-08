package jgluna.potlach.repository;

import jgluna.potlach.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//TODO agregar queries, sino spring-boot revienta
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    ArrayList<User> queryTopUsers(int limit);
}
