package kr.hs.dgsw.web_190326.Controller;

import kr.hs.dgsw.web_190326.Domain.Comment;
import kr.hs.dgsw.web_190326.Protocol.CommentUserNameProtocol;
import kr.hs.dgsw.web_190326.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/listcomment")
    public List<CommentUserNameProtocol> listComments() {
        return this.commentService.listAllComments();
    }

    @GetMapping("/viewcomment/{id}")
    public Comment viewComment(@PathVariable Long id) {
        return this.commentService.findById(id);
    }

    @PostMapping("/addcomment")
    public CommentUserNameProtocol addComment(@RequestBody Comment comment) {
        return this.commentService.addComment(comment);
    }

    @PutMapping("/editcomment/{id}")
    public Comment editComment(@PathVariable Long id, @RequestBody Comment comment) {
        return this.commentService.editComment(id, comment);
    }

    @DeleteMapping("deletecomment/{id}")
    public boolean deleteComment(@PathVariable Long id) {
        return this.commentService.removeComment(id);
    }


}
