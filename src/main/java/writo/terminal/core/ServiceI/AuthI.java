package writo.terminal.core.ServiceI;

import writo.terminal.data.User;
import writo.terminal.util.Res;

import javax.servlet.http.HttpServletRequest;

public interface AuthI {

    Res authenticate(HttpServletRequest request);

    boolean checkLoginToken(String id, String token);

    String cacheLoginToken(User user);

}
