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

}
