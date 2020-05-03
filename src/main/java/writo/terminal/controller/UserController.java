package writo.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import writo.terminal.data.User;
import writo.terminal.mapper.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {this.userMapper = userMapper;}

    /**
     * Get user info by id.
     *
     * @param id ..
     * @return ..
     */
    @GetMapping("search/{id}")
    public List<User> search(@PathVariable int id) {
        return userMapper.getUserById(id);
    }

}
