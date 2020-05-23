package writo.terminal.view;

import lombok.Data;
import writo.terminal.contract.View;
import writo.terminal.data.User;

@Data
public class UserView implements View<User> {

    private long id;
    private String description;
    private String username;
    private String phoneNumber;
    private String email;
    private long level;

}
