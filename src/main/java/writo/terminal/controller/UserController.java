package writo.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import writo.terminal.data.User;
import writo.terminal.mapper.UserMapper;
import writo.terminal.util.Auth;
import writo.terminal.util.Res;
import writo.terminal.view.UserView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserController extends Base {

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {this.userMapper = userMapper;}

    /**
     * Get user info by id.
     */
    @GetMapping("search/{id}")
    public Res search(@PathVariable int id) {
        return new Res(userMapper.getUserById(id).toView());
    }

    /**
     * Register account.
     *
     * @param userView contains username, password, phoneNumber.
     */
    @PostMapping("/register")
    public Res register(@RequestBody UserView userView) {
        userMapper.register(userView);
        return new Res();
    }

    /**
     * Login and add cookies ( "username": username, "tmpToken": hash value generated from password and r.v. )
     */
    @PostMapping("/login")
    public Res login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        User user = userMapper.login(username, password);
        if (null == user) {
            System.out.println("Not found.");
            System.exit(1);
        }
        response.addCookie(new Cookie("username", username));
        response.addCookie(new Cookie("tmpToken", Auth.encode(password)));
        // todo store tmp token
        return new Res();
    }

    /**
     * check whether logged in correctly by cookies, which contains username and tmp token.
     */
    @PostMapping("/authenticate")
    public Res authenticate(HttpServletRequest request) {
        return super.authenticate(request);
    }

}
