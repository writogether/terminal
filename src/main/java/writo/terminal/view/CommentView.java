package writo.terminal.view;

import lombok.Data;
import writo.terminal.contract.View;
import writo.terminal.data.Comment;

@Data
public class CommentView implements View<Comment> {

    private long id;
    private long commenterId;
    private long storyId;
    private String content;

}
