package jgluna.potlach.ws;

import jgluna.potlach.model.User;
import jgluna.potlach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {
    private final UserRepository repository;

    private final String basePath = "/user";

    @Autowired
    public UsersController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = basePath, method = RequestMethod.POST)
    public
    @ResponseBody
    User addUser(@RequestBody User user) {
        return repository.save(user);
    }

    @RequestMapping(value = basePath + "/{email:.+}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUser(@PathVariable("email") String email) {
        return repository.findOne(email);
    }

    @RequestMapping(value = basePath, method = RequestMethod.PUT)
    public
    @ResponseBody
    User updateUser(@RequestBody User user) {
        return repository.save(user);
    }

//    public ArrayList<User> getTopUsers(int limit) {
//        return repository.queryTopUsers(limit);
//    }
}
