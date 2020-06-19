package writo.terminal.controller;

import org.springframework.web.bind.annotation.*;
import writo.terminal.contract.View;
import writo.terminal.data.Story;
import writo.terminal.data.Tree;
import writo.terminal.data.User;
import writo.terminal.type.TagType;
import writo.terminal.util.Res;
import writo.terminal.util.StructTree;
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
        story.setAuthorId((Long) isLogin.getData());

        mapper().story().insert(story);

        if (null == father) {
            Tree tree = new Tree();
            tree.setSExp("(" + story.getId() + ")");
            mapper().tree().insert(tree);

            story.setPath("" + story.getId());
            story.setTreeId(tree.getId());
            story.setDepth(0);
        } else {
            Tree tree = mapper().tree().select(father.getTreeId());
            StructTree structTree = StructTree.deserialize(tree.getSExp());
            structTree.find(father.getId()).add(story.getId());
            tree.setSExp(structTree.serialize());

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
    @GetMapping("/all-story")
    @WellTested
    public Res getAllStory() {
        List<Story> stories = core.mapper().story().all();
        List<StoryView> storyViews=setUserName(stories);
        storyViews.sort((v1, v2) -> v2.getPopularity() - v1.getPopularity());
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
        StoryView s = (StoryView) (core.mapper().story().getStoryById(id)).toView(StoryView.class);
        s.setUserName(core.mapper().user().getUserById(s.getAuthorId()).getUsername());
        return Res.ok(s);
    }

    @GetMapping("/by-father")
    public Res getStoryByFather(@RequestParam long fatherId) {
        List<Story> stories = core.mapper().story().getStoryByFather(fatherId);
        List<StoryView> storyViews=setUserName(stories);
        storyViews.sort((v1, v2) -> v2.getPopularity() - v1.getPopularity());
        return Res.ok(storyViews);
    }

    /**
     * Get story by type.
     */
    @GetMapping("/by-tag")
    @WellTested
    public Res getStoryByTag(@RequestParam TagType tag) {
        List<Story> stories = core.mapper().story().getStoryByType(tag);
        List<StoryView> storyViews=setUserName(stories);
        storyViews.sort((v1, v2) -> v2.getPopularity() - v1.getPopularity());
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


    List<StoryView> setUserName(List<Story> stories){
        List<StoryView> storyViews=new ArrayList<>();
        for(Story s:stories){
            StoryView sv=(StoryView)s.toView(StoryView.class);
            sv.setUserName(core.mapper().user().getUserById(s.getAuthorId()).getUsername());
            storyViews.add(sv);
        }
        return storyViews;
    }

}
