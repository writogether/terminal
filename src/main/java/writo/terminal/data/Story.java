package writo.terminal.data;

import lombok.Data;
import writo.terminal.contract.Entity;
import writo.terminal.type.TagType;

@Data
public class Story implements Entity {

    private long id;
    private long fatherId;
    private long authorId;
    private String title;
    private TagType tag;

    public Story() {
        this.tag = TagType.Other;
    }

}
