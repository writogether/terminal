package writo.terminal.view;

import lombok.Data;
import writo.terminal.contract.View;
import writo.terminal.data.User;

@Data
public class LoginView implements View<User> {

    private String username;
    private String password;

}
