package writo.terminal.view;

import lombok.Data;
import writo.terminal.contract.View;
import writo.terminal.data.Eval;
import writo.terminal.type.EvalType;

@Data
public class EvalView implements View<Eval> {

    private long id;
    private long likerId;
    private long storyId;
    private EvalType type;

}
