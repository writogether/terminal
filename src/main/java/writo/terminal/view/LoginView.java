package writo.terminal.view;

import lombok.Data;

@Data
public class LoginView implements View {

    private String username;
    private String password;

}
