package writo.terminal.data;

import lombok.Data;
import writo.terminal.contract.Entity;

@Data
public class Comment implements Entity {

    private long id;
    private long commenterId;
    private long storyId;
    private String content;

    public Comment() {}

}
