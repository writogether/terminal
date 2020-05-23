package writo.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import writo.terminal.data.User;
import writo.terminal.util.Auth;
import writo.terminal.util.Res;

/**
 * The controller is designed only for backend dev.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final StringRedisTemplate template;
    private final Auth auth;

    @Autowired
    public TestController(StringRedisTemplate template, Auth auth) {
        this.template = template;
        this.auth = auth;
    }

    @GetMapping("/auth")
    public Res test_auth() {
        User user = new User();
        user.setId(1);
        user.setUsername("UU");
        user.setPassword("wao");
        auth.cacheLoginToken(user);
        boolean x = auth.checkLoginToken("1", "token_here");
        System.out.println(x ? "yes" : "no");
        return x ? Res.ok() : Res.oops();
    }

}
