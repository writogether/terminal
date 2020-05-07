package writo.terminal.view;

import lombok.Data;

@Data
public class UserView implements View {

    private long id;
    private String description;
    private String username;
    private String phone_number;
    private String email;
    private long level;

}
