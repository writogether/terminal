package writo.terminal.controller;

import org.springframework.web.bind.annotation.*;
import writo.terminal.contract.View;
import writo.terminal.data.Collect;
import writo.terminal.data.Story;
import writo.terminal.data.StoryContent;
import writo.terminal.data.User;
import writo.terminal.type.TagType;
import writo.terminal.util.Res;
import writo.terminal.util.WellTested;
import writo.terminal.view.StoryView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        Res isLogin = service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        storyView.setAuthorId((Integer) isLogin.getData());

        String content = storyView.getContent();
        mapper().story().uploadStory(storyView);
        mapper().story().upload_story_content(content);
        return Res.ok().setMessage("Upload successfully!");
    }

    /**
     * Delete a story.
     * Delete content only.
     */
    @PostMapping("/delete/{id}")
    @WellTested
    public Res deleteStory(@PathVariable long id, HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;

        core.mapper().story().deleteStory(id, false);
        core.mapper().story().deleteStoryContent(id, "Story deleted.");
        return Res.ok().setMessage("Delete successfully!");
    }

    /**
     * Get concise description for all story.
     *
     * @return contains a list of storyView. (returned by page in future)
     */
    @GetMapping("/allStory")
    @WellTested
    public Res getAllStory() {
        List<Story> stories = core.mapper().story().getAllStory();
        List<View> storyViews = new ArrayList<>();
        for (Story s : stories) {
            StoryContent storyContent = core.mapper().story().getStoryContentById(s.getId());
            String desc = storyContent.getContent();
            if (desc != null)
                if (desc.length() > 80) desc = desc.substring(0, 80);
            storyViews.add(s.toView(StoryView.class));
        }
        return Res.ok(storyViews);
    }

    /**
     * Get story content by id.
     */
    @GetMapping("/content/{id}")
    @WellTested
    public Res getStoryContent(@PathVariable long id) {
        return Res.ok(core.mapper().story().getStoryContentById(id));
    }

    /**
     * Get story by type.
     */
    @GetMapping("/by-type")
    @WellTested
    public Res getStoryByType(@RequestParam TagType tag) {
        List<Story> stories = core.mapper().story().getStoryByType(tag);
        List<View> storyViews = new ArrayList<>();
        for (Story story : stories) {
            storyViews.add(story.toView(StoryView.class));
        }
        return Res.ok(storyViews);
    }

    /**
     * Get someone's all public stories.
     */
    @GetMapping("/by-username")
    @WellTested
    public Res getStoryByUsername(@RequestParam String username) {
        User author = core.mapper().user().getUserByName(username);
        List<Story> stories = core.mapper().story().getStoryByAuthor(author.getId());
        List<StoryView> storyViews = stories.stream().takeWhile(Story::isOpen).map(story -> (StoryView) story.toView(StoryView.class)).collect(Collectors.toList());
        return Res.ok(storyViews);
    }

    /**
     * Get someone's private stories.
     */
    @GetMapping("/private")
    public Res getPrivate(HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        int id = (int) isLogin.getData();
        List<Story> stories = mapper().story().getStoryByAuthor(id);
        List<StoryView> storyViews = stories.stream().filter(story -> !story.isOpen()).map(story -> (StoryView) story.toView(StoryView.class)).collect(Collectors.toList());
        return Res.ok(storyViews);
    }

    /**
     * Collect a story.
     */
    @PostMapping("collect/{id}")
    @WellTested
    public Res collectStory(@PathVariable(name = "id") long storyId, HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        if (core.mapper().collect().checkIfCollected((Integer) isLogin.getData(), storyId).size() > 0)
            return Res.oops("Collected already!");
        Collect collect = new Collect();
        collect.setUserId((Integer) isLogin.getData());
        collect.setStoryId(storyId);
        core.mapper().collect().collectStory(collect);
        return Res.ok().setMessage("Collect Successfully!");
    }

    /**
     * View someone's collection.
     */
    @GetMapping("collect")
    @WellTested
    public Res getCollection(HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        List<Integer> storyIds = core.mapper().story().getStoryByCollector((Integer) isLogin.getData());
        List<Story> stories = new ArrayList<>();
        for (int storyId : storyIds) {
            stories.add(core.mapper().story().getStoryById(storyId));
        }
        return Res.ok(stories);
    }

}
