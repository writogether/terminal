package writo.terminal.controller;

import org.springframework.web.bind.annotation.*;
import writo.terminal.data.Collect;
import writo.terminal.data.Story;
import writo.terminal.type.TagType;
import writo.terminal.util.Res;
import writo.terminal.util.WellTested;
import writo.terminal.view.StoryView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/story")
public class StoryController extends Base {

    /**
     * Upload a story.
     *
     * @param storyView contains contains id, fatherId, authorId, title and (when view story details) content.
     */
    @PostMapping("/upload")
    @WellTested
    public Res upload(@RequestBody StoryView storyView, HttpServletRequest request) {

        Res isLogin = core.service.getAuthS().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        storyView.setAuthorId((Integer) isLogin.getData());

        String content = storyView.getContent();
        core.mapper.getStoryM().uploadStory(storyView);
        core.mapper.getStoryM().upload_story_content(content);
        return Res.ok().setMessage("Upload successfully!");
    }

    /**
     * Delete a story.
     * Delete content only.
     */
    @PostMapping("/delete/{id}")
    public Res deleteStoryContent(@PathVariable long id) {
        core.mapper.getStoryM().delete_story_content(id, "Story deleted.");
        return Res.ok().setMessage("Delete successfully!");
    }

    /**
     * Get concise description for all story.
     *
     * @return contains a list of storyView. (returned by page in future)
     */
    @GetMapping("/allStory")
    public Res getAllStory() {
        return Res.ok(); // todo
    }

    /**
     * Get story content by id.
     */
    @GetMapping("/content/{id}")
    public Res getStoryContent(@PathVariable long id) {
        return Res.ok(core.mapper.getStoryM().getStoryContentById(id));
    }

    @GetMapping("/by-type")
    public Res getStoryByType(TagType tag) {
        return Res.ok(core.mapper.getStoryM().getStoryByType(tag));
    }

    @PostMapping("collect/{id}")
    public Res collectStory(@PathVariable(name = "id") long storyId, HttpServletRequest request) {
        Res isLogin = core.service.getAuthS().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;

        Collect collect = new Collect();
        collect.setUserId((Integer) isLogin.getData());
        collect.setStoryId(storyId);
        core.mapper.getStoryM().collectStory(collect);
        return Res.ok();
    }

    @GetMapping("collect")
    public Res getCollection(HttpServletRequest request) {
        Res isLogin = core.service.getAuthS().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        List<Integer> storyIds = core.mapper.getStoryM().getStoryByCollector((Integer) isLogin.getData());
        List<Story> stories = new ArrayList<>();
        for (int storyId : storyIds) {
            stories.add(core.mapper.getStoryM().getStoryById(storyId));
        }
        return Res.ok(stories);
    }

}
