package kr.hs.dgsw.web_190326.Protocol;

import kr.hs.dgsw.web_190326.Domain.Comment;
import lombok.Data;

@Data
public class CommentUserNameProtocol extends Comment {

    private String username;

    public CommentUserNameProtocol(Comment c, String username) {
        super(c);
        this.username = username;
    }

}
