package writo.terminal.controller;

import org.springframework.web.bind.annotation.*;
import writo.terminal.contract.Entity;
import writo.terminal.contract.View;
import writo.terminal.data.Comment;
import writo.terminal.data.Eval;
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

    /**
     * Comment a story or a comment.
     */
    @PostMapping("/comment")
    @WellTested
    public Res comment(@RequestBody CommentView commentView, HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;

        Comment comment = new Comment();
        comment.setCommenterId(commentView.getCommenterId());
        comment.setContent(commentView.getContent());
        comment.setStoryId(commentView.getStoryId());
        core.mapper().story().commentStory(comment);
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
    @WellTested
    public Res evaluate(@RequestBody EvalView evalView, HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;

        Eval eval = new Eval();
        eval.setStoryId(evalView.getStoryId());
        eval.setType(evalView.getType());
        eval.setUserId(evalView.getLikerId());
        core.mapper().eval().evalStory(eval);
        return Res.ok().setMessage("Evaluate Successfully!");
    }

}
