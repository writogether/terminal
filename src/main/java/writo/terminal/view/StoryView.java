package writo.terminal.view;

import lombok.Data;

@Data
public class StoryView implements View {

    private long id;
    private long fatherId;
    private long authorId;
    private String title;
    private String content;

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
