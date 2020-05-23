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
    private String title;
    private String content;
    private TagType tag;

    public long getId() {
        return id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public long getFatherId() {
        return fatherId;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

}
