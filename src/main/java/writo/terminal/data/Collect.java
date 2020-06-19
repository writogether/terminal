package writo.terminal.data;

import lombok.Data;
import writo.terminal.contract.Entity;

@Data
public class Collect implements Entity {

    private long id;
    private long userId;
    private long storyId;

    public Collect() {}

}
