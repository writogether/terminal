package writo.terminal.controller;

import org.springframework.web.bind.annotation.*;
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

    private final int evalPop = 1;
    private final int commentPop = 2;
    private final int collectPop = 3;

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
        core.mapper().comment().commentStory(comment);
        if (core.mapper().story().getAuthorOfStory(commentView.getStoryId()) != commentView.getCommenterId()) {
            core.mapper().story().updatePopularity(commentPop, commentView.getStoryId());
        }
        return Res.ok().setMessage("Comment Successfully!");
    }

    @GetMapping("/getComment")
    @WellTested
    public Res getComment(@RequestParam long storyId) {
        List<Comment> comments = core.mapper().comment().getComment(storyId);
        List<View> commentViews = new ArrayList<>();
        for (Comment comment : comments) {
            CommentView cv=(CommentView) comment.toView(CommentView.class);
            cv.setUserName(core.mapper().user().getUserById(cv.getCommenterId()).getUsername());
            commentViews.add(cv);
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

        int pop = eval.getType().equals(EvalType.Like) ? evalPop : -evalPop;
        core.mapper().story().updatePopularity(pop, eval.getStoryId());

        return Res.ok().setMessage("Evaluate Successfully!");
    }

    @GetMapping("/getEvaluation")
    public Res getEval(@RequestParam Long storyId,HttpServletRequest request){
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        long userId = (Long) isLogin.getData();
        Eval eval=core.mapper().eval().getEval(storyId, userId);
        if (eval==null)return Res.ok(false);

        return Res.ok(core.mapper().eval().getEval(storyId, userId));
    }

    /**
     * Collect a story.
     */
    @PostMapping("collect")
    public Res collectStory(@RequestParam long storyId, HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        long id = (Long) isLogin.getData();
        Collect collect = mapper().collect().select(id, storyId);
        if (null == collect) {
            collect = new Collect();
            collect.setUserId(id);
            collect.setStoryId(storyId);
            core.mapper().collect().collectStory(collect);
            core.mapper().story().updatePopularity(collectPop, storyId);
            return Res.ok().setMessage("Collect Successfully!");
        } else {
            core.mapper().collect().noCollect(collect);
            core.mapper().story().updatePopularity(-collectPop, storyId);
            return Res.ok().setMessage("Cancel Collect Successfully!");
        }
    }

    @GetMapping("checkIfCollected")
    public Res checkIfCollected(@RequestParam long storyId, HttpServletRequest request){
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        long id = (Long) isLogin.getData();
        boolean collected = mapper().collect().select(id, storyId)!=null;
        return Res.ok(collected);
    }

    /**
     * View someone's collection.
     */
    @GetMapping("collect")
    @WellTested
    public Res getCollection(HttpServletRequest request) {
        Res isLogin = core.service().auth().authenticate(request);
        if (!isLogin.getSuccess()) return isLogin;
        List<Long> storyIds = core.mapper().story().getStoryByCollector((Long) isLogin.getData());
        List<Story> stories = new ArrayList<>();
        for (long storyId : storyIds) {
            stories.add(core.mapper().story().getStoryById(storyId));
        }
        return Res.ok(stories);
    }

}
