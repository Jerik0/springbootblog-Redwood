package com.codeup.blog.repositories;

import com.codeup.blog.models.Comment;
import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comment, Long>{
  List<Comment> findAllById(long id);

  List<Comment> findAllByPost(Post post);

  @Query(
        nativeQuery = true,
        value = "SELECT * FROM comment c WHERE c.post_id = ?1 ORDER BY c.id DESC"
  )
  List<Comment> sortAllByTime(long id);

}