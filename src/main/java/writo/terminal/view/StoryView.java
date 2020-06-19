package writo.terminal.view;

import lombok.Data;
import writo.terminal.contract.View;
import writo.terminal.data.Story;
import writo.terminal.type.TagType;

@Data
public class StoryView implements View<Story> {

    private long id;
    private long fatherId;
    private long authorId;
    private String userName;
    private String title;
    private String content;
    private TagType tag;
    private boolean valid;
    private boolean open;
    private int popularity;
    private int depth;

}
