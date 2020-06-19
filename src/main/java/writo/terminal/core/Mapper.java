package writo.terminal.core;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import writo.terminal.core.mapperI.*;

@Component
@Getter
@Accessors(fluent = true)
public class Mapper {

    private final UserMapper user;
    private final EvalMapper eval;
    private final StoryMapper story;
    private final CommentMapper comment;
    private final CollectMapper collect;
    private final TreeMapper tree;

    public Mapper(UserMapper user, EvalMapper eval, StoryMapper story, CommentMapper comment, CollectMapper collect, TreeMapper tree) {
        this.user = user;
        this.eval = eval;
        this.story = story;
        this.comment = comment;
        this.collect = collect;
        this.tree = tree;
    }

}
