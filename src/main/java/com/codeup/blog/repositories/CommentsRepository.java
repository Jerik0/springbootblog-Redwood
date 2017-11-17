package com.codeup.blog.repositories;

import com.codeup.blog.models.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comment, Long>{
  public List<Comment> findAllById(long id);
}
