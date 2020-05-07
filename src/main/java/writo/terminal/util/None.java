package writo.terminal.util;

import lombok.Data;
import writo.terminal.view.View;

@Data
public class None implements View {

    private String hint = "API supposed to return nothing";

}
