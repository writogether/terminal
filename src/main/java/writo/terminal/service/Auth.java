package writo.terminal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import writo.terminal.core.ServiceI.AuthI;
import writo.terminal.data.User;
import writo.terminal.util.Res;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class Auth implements AuthI {

    private final StringRedisTemplate template;

    @Autowired
    public Auth(StringRedisTemplate template) {this.template = template;}

    private static String encode(int key) {
        long time = new Date().getTime();
        long wow = (key * time) % (int) (1e9 + 7);
        String raw = Long.toHexString(wow);
        return DigestUtils.md5DigestAsHex(raw.getBytes());
    }

    public Res authenticate(HttpServletRequest request) {
        String id = null;
        String loginToken = null;
        Cookie[] cookies = request.getCookies();
        if (null == cookies) return Res.oops().setMessage("No cookies");
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("id")) id = cookie.getValue();
            else if (cookie.getName().equals("loginToken")) loginToken = cookie.getValue();
        if (null == id) return Res.oops().setMessage("No cookies named id");
        if (null == loginToken) return Res.oops().setMessage("No cookies named loginToken");
        System.out.println("Try to authenticate id: " + id);
        boolean ok = checkLoginToken(id, loginToken);

        return ok ? Res.ok(Long.parseLong(id)) : Res.oops().setMessage("login please.");
    }

    public boolean checkLoginToken(String id, String token) {
        Object actual = template.opsForHash().get("login_token", id);
        if (actual == null) {
            System.out.println("No token found in server");
            return false;
        }
        System.out.println(actual.toString());
        System.out.println(token);
        return actual.toString().equals(token);
    }

    public String cacheLoginToken(User user) {
        String key = String.valueOf(user.getId());
        String loginToken = encode(user.hashCode());
        template.opsForHash().put("login_token", key, loginToken);
        return loginToken;
    }

}
