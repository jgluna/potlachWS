package jgluna.potlach.ws;

import jgluna.potlach.model.User;
import jgluna.potlach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = basePath + "/{email}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUser(@PathVariable("email") String email) {
        return repository.findOne(email);
    }

    @RequestMapping(value = basePath, method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        User temp = repository.save(user);
        if (temp == null) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

//    public ArrayList<User> getTopUsers(int limit) {
//        return repository.queryTopUsers(limit);
//    }
}
