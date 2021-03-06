package writo.terminal.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.web.bind.annotation.*;
import writo.terminal.data.User;
import writo.terminal.util.Res;
import writo.terminal.util.WellTested;
import writo.terminal.view.LoginView;
import writo.terminal.view.RegisterView;
import writo.terminal.view.UserView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserController extends Base {

    /**
     * Get user info by id.
     */
    @WellTested
    @GetMapping("/search/{id}")
    public Res search(@PathVariable long id) {
        User user = mapper().user().getUserById(id);
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
        if (null != mapper().user().getUserByPhoneNumber(registerView.getPhoneNumber())
                || null != mapper().user().getUserByName(registerView.getUsername())) {
            return Res.oops().setMessage("username or phone number existed!");
        }
        mapper().user().register(registerView);
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
        User user = mapper().user().getUserByName(loginView.getUsername());
        if (null == user) user = mapper().user().getUserByPhoneNumber(loginView.getUsername());
        if (null == user) return Res.oops().setMessage("username not existed.");
        if (!user.getPassword().equals(DigestUtil.md5Hex(loginView.getPassword()))) {
            return Res.oops().setMessage("wrong password.");
        }

        int expiry = 86400 * 14; // cookies saved for 14 days.
        Cookie idCki = new Cookie("id", String.valueOf(user.getId()));
        idCki.setMaxAge(expiry);
        idCki.setPath("/api");
        response.addCookie(idCki);

        String token = service().auth().cacheLoginToken(user);
        Cookie loginTokenCki = new Cookie("loginToken", token);
        loginTokenCki.setMaxAge(expiry);
        loginTokenCki.setPath("/api");
        response.addCookie(loginTokenCki);

        return Res.ok(user).setMessage("Login successfully!");
    }

    /**
     * check whether logged in correctly by cookies, which contains username and login token.
     */
    @WellTested
    @PostMapping("/authenticate")
    public Res authenticate(HttpServletRequest request) {
        return service().auth().authenticate(request);
    }

    /**
     * user info admin
     */
    @PostMapping("/update")
    public Res updateUserInfo(@RequestBody UserView userView) {
        long id = userView.getId();
        User user = mapper().user().getUserById(id);
//        if (!(null == userView.getUsername() || userView.getUsername().equals(user.getUsername()))) {
//            if (exists(userView)) return Res.oops().setMessage("username or phone number existed!");
//        } else
        BeanUtil.copyProperties(userView, user, CopyOptions.create().ignoreNullValue());
        mapper().user().updateById(user);
        return Res.ok().setMessage("Information modified successfully!");
    }

    /**
     * Judge whether a username or phone number already used.
     */
    private boolean exists(UserView userView) {
        return mapper().user().getUserByName(userView.getUsername()) != null
                || mapper().user().getUserByPhoneNumber(userView.getPhoneNumber()) != null;

    }

}
