package writo.terminal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import writo.terminal.data.User;
import writo.terminal.util.Res;

/**
 * The controller is designed only for backend dev.
 */
@RestController
@RequestMapping("/test")
public class TestController extends Base {

    @GetMapping("/x")
    public Res testX() {
        User r = mapper().user().getUserById(1);
        System.out.println("X");
        return Res.ok(r);
    }

}
