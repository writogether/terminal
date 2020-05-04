package writo.terminal.controller;

import writo.terminal.util.Res;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Base {

    public Res authenticate(HttpServletRequest request) {
        String username = null;
        String tmpToken = null;
        Cookie[] cookies = request.getCookies();
        if (null == cookies) return new Res("No cookies.");
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("username")) username = cookie.getValue();
            else if (cookie.getName().equals("tmpToken")) tmpToken = cookie.getValue();
        if (null == username) return new Res("No cookies named username.");
        // todo check tmp token.
        System.out.println("My username is " + username);
        return new Res();
    }

}
