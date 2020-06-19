package writo.terminal.data;

import lombok.Data;

@Data
public class Tree {

    long id;
    String sExp;

    public Tree() {
        sExp = "";
    }

}
