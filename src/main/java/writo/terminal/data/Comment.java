package writo.terminal.data;

import lombok.Data;
import writo.terminal.view.View;

@Data
public class Comment implements Entity {

    private long id;
    private long commenterId;
    private long storyId;
    private String content;

    @Override
    public View toView() {
        return null;
    }

}
