package writo.terminal.view;

import lombok.Data;
import writo.terminal.contract.View;
import writo.terminal.data.User;

@Data
public class RegisterView implements View<User> {

    private Long id;
    private String username;
    private String password;
    private String phoneNumber;

}
