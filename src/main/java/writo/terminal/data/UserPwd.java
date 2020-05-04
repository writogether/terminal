package writo.terminal.data;

import writo.terminal.view.View;

public class UserPwd implements Entity{

    private long id;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public View toView() {
        return null;
    }

}
