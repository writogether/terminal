package writo.terminal.data;

import lombok.Data;
import writo.terminal.contract.Entity;
import writo.terminal.type.TagType;

@Data
public class Story implements Entity {

    private long id;
    private long authorId;
    private String title;
    private TagType tag;
    private boolean valid;
    private boolean open;
    private int popularity;
    private int depth;
    private long treeId;
    private String path;

    public Story() {
        authorId = -1;
        title = "";
        tag = TagType.Other;
        valid = true;
        open = true;
        popularity = 0;
        depth = -1;
        treeId = -1;
    }

}
