package writo.terminal.view;

import lombok.Data;
import writo.terminal.contract.View;
import writo.terminal.data.Eval;
import writo.terminal.type.EvalType;

@Data
public class EvalView implements View<Eval> {

    private Long id;
    private Long likerId;
    private Long storyId;
    private EvalType type;

}
