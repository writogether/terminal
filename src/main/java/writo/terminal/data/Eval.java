package writo.terminal.data;

import writo.terminal.contract.Entity;
import writo.terminal.type.EvalType;

public class Eval implements Entity {

    private long id;
    private long userId;
    private long storyId;
    private EvalType type;

    public Eval() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getStoryId() {
        return storyId;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public EvalType getType() {
        return type;
    }

    public void setType(EvalType type) {
        this.type = type;
    }

}
