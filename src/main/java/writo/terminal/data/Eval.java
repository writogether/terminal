package writo.terminal.data;

import lombok.Data;
import writo.terminal.contract.Entity;
import writo.terminal.type.EvalType;

@Data
public class Eval implements Entity {

    private long id;
    private long userId;
    private long storyId;
    private EvalType type;

    public Eval() {}

}
