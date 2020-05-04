package writo.terminal.data;

import writo.terminal.view.View;

public class StoryContent implements Entity{

    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public View toView() {
        return null;
    }

}
