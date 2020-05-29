package writo.terminal.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import writo.terminal.data.Comment;
import writo.terminal.data.Eval;
import writo.terminal.util.Res;
import writo.terminal.util.WellTested;
import writo.terminal.view.CommentView;
import writo.terminal.view.EvalView;

import javax.servlet.http.HttpServletRequest;

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
        return Res.ok("Comment Successfully!");
    }

    /**
     * Evaluate a story or a comment.
     */
    @PostMapping("/evaluate")
    public Res evaluate(@RequestBody EvalView evalView,HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;

        Eval eval=new Eval();
        eval.setStoryId(evalView.getStoryId());
        eval.setType(evalView.getType());
        eval.setUserId(evalView.getLikerId());
        core.mapper().eval().evalStory(eval);
        return Res.ok("Evaluate Successfully!");
    }

}
