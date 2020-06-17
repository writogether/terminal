package writo.terminal.controller;

import org.springframework.web.bind.annotation.*;
import writo.terminal.contract.Entity;
import writo.terminal.contract.View;
import writo.terminal.data.Collect;
import writo.terminal.data.Comment;
import writo.terminal.data.Eval;
import writo.terminal.data.Story;
import writo.terminal.type.EvalType;
import writo.terminal.util.Res;
import writo.terminal.util.WellTested;
import writo.terminal.view.CommentView;
import writo.terminal.view.EvalView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/interact")
public class InteractController extends Base {
    private int evalPop=1;
    private int commentPop=2;
    private int collectPop=3;

    /**
     * Comment a story or a comment.
     */
    @PostMapping("/comment")
    public Res comment(@RequestBody CommentView commentView, HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;

        Comment comment = new Comment();
        comment.setCommenterId(commentView.getCommenterId());
        comment.setContent(commentView.getContent());
        comment.setStoryId(commentView.getStoryId());
        core.mapper().story().commentStory(comment);
        if(core.mapper().story().getAuthorOfStory(commentView.getStoryId())!=commentView.getCommenterId()) {
            core.mapper().story().updatePopularity(commentPop, commentView.getStoryId());
        }
        return Res.ok().setMessage("Comment Successfully!");
    }

    @GetMapping("/getComment")
    @WellTested
    public Res getComment(@RequestParam long storyId) {
        List<Comment> comments = core.mapper().story().getComment(storyId);
        List<View> commentViews = new ArrayList<>();
        for (Comment comment : comments) {
            commentViews.add(comment.toView(CommentView.class));
        }
        return Res.ok(commentViews);
    }

    /**
     * Evaluate a story or a comment.
     */
    @PostMapping("/evaluate")
    public Res evaluate(@RequestBody EvalView evalView, HttpServletRequest request) {

        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;

        Eval eval = new Eval();
        eval.setStoryId(evalView.getStoryId());
        eval.setType(evalView.getType());
        eval.setUserId(evalView.getLikerId());
        core.mapper().eval().evalStory(eval);

        int pop=eval.getType().equals(EvalType.Like)?evalPop:-evalPop;
        core.mapper().story().updatePopularity(pop,eval.getStoryId());

        return Res.ok().setMessage("Evaluate Successfully!");
    }

    /**
     * Collect a story.
     */
    @PostMapping("collect/{id}")
    public Res collectStory(@PathVariable(name = "id") long storyId, HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        if (core.mapper().collect().checkIfCollected((Integer) isLogin.getData(), storyId).size() > 0)
            return Res.oops("Collected already!");
        Collect collect = new Collect();
        collect.setUserId((Integer) isLogin.getData());
        collect.setStoryId(storyId);
        core.mapper().collect().collectStory(collect);
        core.mapper().story().updatePopularity(collectPop,storyId);
        return Res.ok().setMessage("Collect Successfully!");
    }

    @PostMapping("noCollect/{id}")
    public Res noCollectStory(@PathVariable(name = "id") long storyId, HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        Collect collect = new Collect();
        collect.setUserId((Integer) isLogin.getData());
        collect.setStoryId(storyId);
        core.mapper().collect().noCollect(collect);
        core.mapper().story().updatePopularity(-collectPop,storyId);
        return Res.ok().setMessage("Cancel Collect Successfully!");
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
