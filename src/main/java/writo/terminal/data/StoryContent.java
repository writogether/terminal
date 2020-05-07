package writo.terminal.data;

import lombok.Data;
import writo.terminal.view.View;

@Data
public class StoryContent implements Entity {

    private long id;
    private String content;

    public View toView() {
        return null;
    }

}
