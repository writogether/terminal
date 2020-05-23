package writo.terminal.controller;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import writo.terminal.data.User;
import writo.terminal.mapper.UserMapper;
import writo.terminal.util.WellTested;
import writo.terminal.util.Auth;
import writo.terminal.util.Res;
import writo.terminal.view.LoginView;
import writo.terminal.view.RegisterView;
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
    @WellTested
    @GetMapping("search/{id}")
    public Res search(@PathVariable long id) {
        User user = userMapper.getUserById(id);
        return Res.ok(user.toView(UserView.class));
    }

    /**
     * Register account.
     *
     * @param registerView contains username, password, phoneNumber.
     */
    @WellTested
    @PostMapping("/register")
    public Res register(@RequestBody RegisterView registerView) {
        if (null != userMapper.getUserByPhoneNumber(registerView.getPhoneNumber())
                || null != userMapper.getUserByName(registerView.getUsername())) {
            return Res.oops().setMessage("user already existed.");
        }
        userMapper.register(registerView);
        return Res.ok().setMessage("Register successfully!");

    }

    /**
     * login and add cookies.
     * user can log in with its username or phone number, which are both unique.
     * username starts with letter.
     */
    @WellTested
    @PostMapping("/login")
    public Res login(@RequestBody LoginView loginView, HttpServletResponse response) {
        User user = userMapper.getUserByName(loginView.getUsername());
        if (null == user) user = userMapper.getUserByPhoneNumber(loginView.getUsername());
        if (null == user) return Res.oops().setMessage("username not existed.");
        if (!user.getPassword().equals(DigestUtil.md5Hex(loginView.getPassword()))) {
            return Res.oops().setMessage("wrong password.");
        }

        int expiry = 86400 * 14; // cookies saved for 14 days.
        Cookie idCki = new Cookie("id", String.valueOf(user.getId()));
        idCki.setMaxAge(expiry);
        idCki.setPath("/api");
        response.addCookie(idCki);

        String token = auth.cacheLoginToken(user);
        Cookie loginTokenCki = new Cookie("loginToken", token);
        loginTokenCki.setMaxAge(expiry);
        loginTokenCki.setPath("/api");
        response.addCookie(loginTokenCki);

        return Res.ok().setMessage("Login successfully!");
    }

    /**
     * check whether logged in correctly by cookies, which contains username and login token.
     */
    @WellTested
    @PostMapping("/authenticate")
    public Res authenticate(HttpServletRequest request) {
        return auth.authenticate(request);
    }

    /**
     * user info admin
     */
    @PostMapping("/update")
    public Res updateUserInfo(@RequestBody UserView userView) {
        long id = userView.getId();
        String description = userView.getDescription();
        String username = userView.getUsername();
        String email = userView.getEmail();
        String phone_number = userView.getPhoneNumber();
        userMapper.updateById(id, description, username, email, phone_number);
        return Res.ok().setMessage("Information modified successfully!");
    }

}
