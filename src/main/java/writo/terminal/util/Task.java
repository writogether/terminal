package writo.terminal.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import writo.terminal.core.mapperI.StoryMapper;

@EnableScheduling
@Component
public class Task {

    private final StoryMapper storyMapper;

    @Autowired
    public Task(StoryMapper storyMapper) {this.storyMapper = storyMapper;}

    @Scheduled(cron = "0 0 12 * * ?")
    void coolDown() {
        storyMapper.coolDown();
    }

}
