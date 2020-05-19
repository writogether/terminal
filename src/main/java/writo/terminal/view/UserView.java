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

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }
}
