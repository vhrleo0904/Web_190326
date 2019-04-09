package kr.hs.dgsw.web_190326.Repository;

import kr.hs.dgsw.web_190326.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
