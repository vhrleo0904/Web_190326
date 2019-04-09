package kr.hs.dgsw.web_190326.Service;

import kr.hs.dgsw.web_190326.Domain.Comment;
import kr.hs.dgsw.web_190326.Protocol.CommentUserNameProtocol;

import java.util.List;

public interface CommentService {

    List<CommentUserNameProtocol> listAllComments();

    Comment findById(Long id);

    CommentUserNameProtocol addComment(Comment comment);

    Comment editComment(Long id, Comment comment);

    boolean removeComment(Long id);

}
