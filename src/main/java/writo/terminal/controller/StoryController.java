package writo.terminal.controller;

import org.springframework.web.bind.annotation.*;
import writo.terminal.contract.View;
import writo.terminal.data.Story;
import writo.terminal.data.Tree;
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
     * Set fatherId to -1 when creating a new story.
     */
    @PostMapping("/upload")
    public Res upload(@RequestBody StoryView storyView, HttpServletRequest request) {
        Res isLogin = service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;

        Story father = mapper().story().getStoryById(storyView.getFatherId());
        Story story = storyView.toEntity();
        story.setAuthorId((Integer) isLogin.getData());

        mapper().story().insert(story);

        if (null == father) {
            Tree tree = new Tree();
            tree.setTree("(" + story.getId() + ")");
            mapper().tree().insert(tree);
            story.setPath("" + story.getId());
            story.setTreeId(tree.getId());
            story.setDepth(0);
        } else {
            story.setPath(father.getPath() + "," + story.getId());
            story.setTreeId(father.getTreeId());
            story.setDepth(father.getDepth() + 1);
        }

        mapper().story().update(story);
        mapper().story().uploadStoryContent(storyView.getContent());
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
            storyViews.add(s.toView(StoryView.class));
        }
        storyViews.sort((o1, o2) -> {
            StoryView v1 = (StoryView) o1;
            StoryView v2 = (StoryView) o2;
            return v2.getPopularity() - v1.getPopularity();
        });
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

    @GetMapping("/storyInfo/{id}")
    public Res getStoryById(@PathVariable long id) {
        return Res.ok(core.mapper().story().getStoryById(id));
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

}
