package writo.terminal.controller;

import org.springframework.web.bind.annotation.*;
import writo.terminal.util.Res;
import writo.terminal.view.StoryContentView;
import writo.terminal.view.StoryView;

@RestController
@RequestMapping("/api/story")
public class StoryController extends Base {

    /**
     * Upload a story.
     *
     * @param storyView   contains contains id, fatherId, authorId and title.
     * @param contentView contains main content of story.
     */
    @PostMapping("/upload")
    public Res upload(@RequestBody StoryView storyView, @RequestBody StoryContentView contentView) {
        return new Res(); // todo
    }

    /**
     * Get concise description for all story.
     *
     * @return contains a list of storyView. (returned by page in future)
     */
    @GetMapping("/allStory")
    public Res getAllStory() {
        return new Res(); // todo
    }

    /**
     * Get story content by id.
     */
    @GetMapping("/content/{id}")
    public Res getStoryContent(@PathVariable String id) {
        return null; // todo
    }

}
