package writo.terminal.util;

import writo.terminal.view.View;

public class None implements View {

    private String hint = "API supposed to return nothing.";

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }

}
