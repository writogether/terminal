package writo.terminal.core.ServiceI;

import writo.terminal.data.User;
import writo.terminal.util.Res;

import javax.servlet.http.HttpServletRequest;

/**
 * Identification relative service.
 */
public interface AuthI {

    /**
     * Return whether request sender has logged in.
     */
    Res authenticate(HttpServletRequest request);

    /**
     * Check given token's validity.
     */
    boolean checkLoginToken(String id, String token);

    /**
     * Cache/Update temp token for user after successful login.
     */
    String cacheLoginToken(User user);

}
