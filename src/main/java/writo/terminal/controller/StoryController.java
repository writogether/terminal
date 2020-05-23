package writo.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import writo.terminal.data.Collect;
import writo.terminal.mapper.StoryMapper;
import writo.terminal.type.TagType;
import writo.terminal.util.Auth;
import writo.terminal.util.Res;
import writo.terminal.util.WellTested;
import writo.terminal.view.StoryView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/story")
public class StoryController {

    private final StoryMapper storyMapper;
    private final Auth auth;

    @Autowired
    public StoryController(StoryMapper storyMapper, Auth auth, HttpServletRequest request) {
        this.storyMapper = storyMapper;
        this.auth = auth;
    }

    /**
     * Upload a story.
     *
     * @param storyView contains contains id, fatherId, authorId, title and (when view story details) content.
     */
    @PostMapping("/upload")
    @WellTested
    public Res upload(@RequestBody StoryView storyView, HttpServletRequest request) {

        Res isLogin = auth.authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        storyView.setAuthorId((Integer) isLogin.getData());

        String content = storyView.getContent();
        storyMapper.uploadStory(storyView);
        storyMapper.upload_story_content(content);
        return Res.ok().setMessage("Upload successfully!");
    }

    /**
     * Delete a story.
     * Delete content only.
     */
    @PostMapping("/delete/{id}")
    public Res deleteStoryContent(@PathVariable long id) {
        storyMapper.delete_story_content(id, "Story deleted.");
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
        return Res.ok(storyMapper.getStoryContentById(id));
    }

    @GetMapping("/get-by-type")
    public Res getStoryByType(TagType tag) {
        return Res.ok(storyMapper.getStoryByType(tag));
    }

    @PostMapping("collect/{id}")
    public Res collectStory(@PathVariable(name = "id") long storyId, HttpServletRequest request) {
        Res isLogin = auth.authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        Collect collect = new Collect();
        collect.setUserId((Integer) isLogin.getData());
        collect.setStoryId(storyId);
        storyMapper.collectStory(collect);
        return Res.ok();
    }

}
