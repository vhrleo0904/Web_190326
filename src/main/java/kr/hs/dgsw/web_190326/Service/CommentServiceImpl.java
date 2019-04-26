package kr.hs.dgsw.web_190326.Service;

import kr.hs.dgsw.web_190326.Domain.Comment;
import kr.hs.dgsw.web_190326.Domain.User;
import kr.hs.dgsw.web_190326.Protocol.CommentUserNameProtocol;
import kr.hs.dgsw.web_190326.Repository.CommentRepository;
import kr.hs.dgsw.web_190326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        User u = this.userRepository.save(new User("aaa", "aaa", "aaa"));
        /*this.commentRepository.save(new Comment(u.getId(), "hi there 111"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 222"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 333"));*/
    }

    @Override
    public List<CommentUserNameProtocol> listAllComments() {
        List<Comment> commentList =  this.commentRepository.findAll();
        List<CommentUserNameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(comment.getUserId());
            String username = (found.isPresent()) ? found.get().getUsername() : null;
            cupList.add(new CommentUserNameProtocol(comment, username));
        });

        return cupList;
    }

    @Override
    public Comment findById(Long id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if(comment.isPresent())
            return comment.get();
        else
            return null;
    }

    @Override
    public CommentUserNameProtocol addComment(Comment comment) {
        return new CommentUserNameProtocol(
                this.commentRepository.save(comment),
                this.userRepository.findById(comment.getUserId())
                    .map(found -> found.getUsername())
                    .orElse(null)
                );
    }

    @Override
    public Comment editComment(Long id, Comment comment) {
        return this.commentRepository.findById(id)
                .map(f -> {
                    f.setContent(Optional.ofNullable(comment.getContent()).orElse(f.getContent()));
                    return this.commentRepository.save(f);
                })
                .orElse(null);
    }

    @Override
    public boolean removeComment(Long id) {
        try {
            this.commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
