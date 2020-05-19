package writo.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import writo.terminal.data.User;
import writo.terminal.mapper.UserMapper;
import writo.terminal.util.Auth;
import writo.terminal.util.Res;
import writo.terminal.view.LoginView;
import writo.terminal.view.UserView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserMapper userMapper;
    private final Auth auth;

    @Autowired
    public UserController(UserMapper userMapper, Auth auth) {
        this.userMapper = userMapper;
        this.auth = auth;
    }

    /**
     * Get user info by id.
     */
    @GetMapping("search/{id}")
    public Res search(@PathVariable long id) {
        return new Res(userMapper.getUserById(id));
    }

    /**
     * Register account.
     * @param userView contains username, password, phoneNumber.
     */
    @PostMapping("/register")
    public Res register(@RequestBody UserView userView) {
        String phone_number=userView.getPhone_number();
        boolean user_existed=userMapper.checkIfExisted(phone_number);
        if(user_existed){
            System.out.println("User existed");
            System.exit(1);
        }
        else {
            userMapper.register(userView);
        }
        userMapper.register(userView);
        return new Res("Register successfully!");
    }

    /**
     * Login and add cookies.
     */
    @PostMapping("/login")
    public Res login(@RequestBody LoginView login, HttpServletResponse response) {
        String username = login.getUsername();
        String password = login.getPassword();
        User user = userMapper.login(username, password);
        if (null == user) {
            System.out.println("User not found");
            System.exit(1);
        }
        String token = auth.cache_login_token(user);

        int expiry = 86400 * 14; // cookies saved for 14 days.
        Cookie idCki = new Cookie("id", String.valueOf(user.getId()));
        idCki.setMaxAge(expiry);
        response.addCookie(idCki);

        Cookie loginTokenCki = new Cookie("loginToken", token);
        loginTokenCki.setMaxAge(expiry);
        response.addCookie(loginTokenCki);

        return new Res("Login successfully!");
    }

    /**
     * check whether logged in correctly by cookies, which contains username and login token.
     */
    @PostMapping("/authenticate")
    public Res authenticate(HttpServletRequest request) {
        return auth.authenticate(request);
    }

    /**
     * user info admin
     */
    @PostMapping("/update")
    public Res upadateUserInfo(@RequestBody UserView userView){
        Long id=userView.getId();
        String description=userView.getDescription();
        String username=userView.getUsername();
        String email=userView.getEmail();
        String phone_number=userView.getPhone_number();
        userMapper.updateById(id,description,username,email,phone_number);
        return new Res("Information modified successfully!");
    }

}
