package writo.terminal.view;

import lombok.Data;

@Data
public class CommentView implements View {

    private long id;
    private long commenterId;
    private long storyId;
    private String content;

}
