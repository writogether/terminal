package writo.terminal.data;

import lombok.Data;
import writo.terminal.contract.Entity;

@Data
public class User implements Entity {

    private long id;
    private String description;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private long level;

    public User() {
        description = "";
    }
}
