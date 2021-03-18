package io.github.mikhirurg.teammanager.repo;

import io.github.mikhirurg.teammanager.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
