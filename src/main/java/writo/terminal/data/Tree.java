package writo.terminal.data;

import lombok.Data;

@Data
public class Tree {

    long id;
    String tree;

    public Tree() {
        tree = "";
    }

}
