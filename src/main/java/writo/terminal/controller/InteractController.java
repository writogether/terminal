package writo.terminal.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import writo.terminal.util.Res;
import writo.terminal.view.CommentView;
import writo.terminal.view.EvalView;

@RestController
@RequestMapping("/api/interact")
public class InteractController extends Base {

    /**
     * Comment a story or a comment.
     */
    @PostMapping("/comment")
    public Res comment(@RequestBody CommentView commentView) {
        return null; // todo
    }

    /**
     * Evaluate a story or a comment.
     */
    @PostMapping("/evaluate")
    public Res evaluate(@RequestBody EvalView evalView) {
        return null; // todo
    }

}
