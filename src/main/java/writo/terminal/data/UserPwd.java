package writo.terminal.data;

import lombok.Data;
import writo.terminal.view.View;

@Data
public class UserPwd implements Entity {

    private long id;
    private String password;

    @Override
    public View toView() {
        return null;
    }

}
