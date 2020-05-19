package writo.terminal.view;

import lombok.Data;

@Data
public class LoginView implements View {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
