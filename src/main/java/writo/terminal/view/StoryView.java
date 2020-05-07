package writo.terminal.view;

import lombok.Data;

@Data
public class StoryView implements View {

    private long id;
    private long fatherId;
    private long authorId;
    private String title;
    private String content;

}
