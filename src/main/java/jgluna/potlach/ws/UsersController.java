package jgluna.potlach.ws;

import jgluna.potlach.model.User;
import jgluna.potlach.repository.RepositoryInterface;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class UsersController {
    private final RepositoryInterface repository;

    public UsersController(RepositoryInterface repository) {
        this.repository = repository;
    }

    public User addUser(User user) {
        return repository.saveUser(user);
    }

    public User getUser(String email) {
        return repository.findUserByEmail(email);
    }

    public boolean updateUser(User user) {
        User temp = repository.updateUser(user);
        return temp != null;
    }

    public ArrayList<User> getTopUsers(int limit){
        return repository.queryTopUsers(limit);
    }
}
