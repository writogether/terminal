package writo.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import writo.terminal.mapper.StoryMapper;
import writo.terminal.mapper.UserMapper;
import writo.terminal.util.Auth;
import writo.terminal.util.Res;
import writo.terminal.view.StoryView;

@RestController
@RequestMapping("/api/story")
public class StoryController {

    private final StoryMapper storyMapper;
    private final Auth auth;
    @Autowired
    public StoryController(StoryMapper storyMapper, Auth auth) {
        this.storyMapper = storyMapper;
        this.auth = auth;
    }
    /**
     * Upload a story.
     *
     * @param storyView contains contains id, fatherId, authorId, title and (when view story details) content.
     */
    @PostMapping("/upload")
    public Res upload(@RequestBody StoryView storyView) {
        long father_id=storyView.getId();
        long author_id=storyView.getAuthorId();
        String title=storyView.getTitle();
        String content=storyView.getContent();
        storyMapper.uploadStory(father_id,author_id,title);
        storyMapper.upload_story_content(content);
        return new Res("Upload successfully!");
    }

    /**
     * Delete a story.
     * Delete content only
     */
    @PostMapping("/content/{id}")
    public Res deleteStoryContent(@PathVariable long id){
        storyMapper.delete_story_content(id,"Story deleted.");
        return new Res("Delete successfully!");
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
    public Res getStoryContent(@PathVariable long id) {
        return new Res(storyMapper.getStoryContentById(id));
    }

}
