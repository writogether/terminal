package writo.terminal.data;

import writo.terminal.view.View;

public class Eval implements Entity {

    private long id;
    private long likerId;
    private long storyId;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLikerId() {
        return likerId;
    }

    public void setLikerId(long likerId) {
        this.likerId = likerId;
    }

    public long getStoryId() {
        return storyId;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public View toView() {
        return null;
    }

}
