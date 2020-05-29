package writo.terminal.core;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import writo.terminal.core.mapperI.CollectMapper;
import writo.terminal.core.mapperI.EvalMapper;
import writo.terminal.core.mapperI.StoryMapper;
import writo.terminal.core.mapperI.UserMapper;

@Component
@Getter
@Accessors(fluent = true)
public class Mapper {

    private final UserMapper user;
    private final EvalMapper eval;
    private final StoryMapper story;
    private final CollectMapper collect;

    public Mapper(UserMapper user, EvalMapper eval, StoryMapper story, CollectMapper collect) {
        this.user = user;
        this.eval = eval;
        this.story = story;
        this.collect = collect;
    }

}
