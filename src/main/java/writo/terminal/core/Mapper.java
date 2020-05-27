package writo.terminal.core;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import writo.terminal.core.mapperI.CollectMapper;
import writo.terminal.core.mapperI.EvalMapper;
import writo.terminal.core.mapperI.StoryMapper;
import writo.terminal.core.mapperI.UserMapper;

@Component
@Getter
public class Mapper {

    private final UserMapper userM;
    private final EvalMapper evalM;
    private final StoryMapper storyM;
    private final CollectMapper collectM;

    @Autowired
    public Mapper(UserMapper userM, EvalMapper evalM, StoryMapper storyM, CollectMapper collectM) {
        this.userM = userM;
        this.evalM = evalM;
        this.storyM = storyM;
        this.collectM = collectM;
    }

}
