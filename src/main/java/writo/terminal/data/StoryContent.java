package writo.terminal.data;

import lombok.Data;
import writo.terminal.contract.Entity;

@Data
public class StoryContent implements Entity {

    private long id;
    private String content;

    public StoryContent() {}

}
