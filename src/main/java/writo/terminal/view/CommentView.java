package writo.terminal.view;

import lombok.Data;
import writo.terminal.contract.View;
import writo.terminal.data.Comment;

@Data
public class CommentView implements View<Comment> {

    private Long id;
    private Long commenterId;
    private Long storyId;
    private String userName;
    private String content;

}
