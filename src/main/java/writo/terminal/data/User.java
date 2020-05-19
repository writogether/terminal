package writo.terminal.data;

import lombok.Data;
import writo.terminal.view.View;

@Data
public class User implements Entity {

    private long id;
    private String description;
    private String username;
    private String password;
    private String phone_number;
    private String email;
    private long level;

    @Override
    public View toView() {
        return null;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
