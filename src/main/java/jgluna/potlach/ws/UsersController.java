package jgluna.potlach.ws;

import jgluna.potlach.model.User;
import jgluna.potlach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class UsersController {
    private final UserRepository repository;

    @Autowired
    public UsersController(UserRepository repository) {
        this.repository = repository;
    }

    public User addUser(User user) {
        return repository.save(user);
    }

    public User getUser(String email) {
        return repository.findOne(email);
    }

    public boolean updateUser(User user) {
        User temp = repository.save(user);
        return temp != null;
    }

    public ArrayList<User> getTopUsers(int limit) {
        return repository.queryTopUsers(limit);
    }
}
