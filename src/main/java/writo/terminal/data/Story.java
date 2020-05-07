package writo.terminal.data;

import lombok.Data;
import writo.terminal.view.View;

@Data
public class Story implements Entity {

    private long id;
    private long fatherId;
    private long authorId;
    private String title;

    @Override
    public View toView() {
        return null;
    }

}
