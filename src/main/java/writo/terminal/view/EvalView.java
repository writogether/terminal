package writo.terminal.view;

import lombok.Data;

@Data
public class EvalView implements View {

    private long id;
    private long likerId;
    private long storyId;
    private String type;

}
