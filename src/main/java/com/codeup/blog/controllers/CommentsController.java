package com.codeup.blog.controllers;

import com.codeup.blog.models.Comment;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.CommentsRepository;
import com.codeup.blog.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentsController {

  private final CommentsRepository commentsDao;
  private final PostsRepository postsDao;

  @Autowired
  public CommentsController(CommentsRepository commentsDao, PostsRepository postsDao) {
    this.commentsDao = commentsDao;
    this.postsDao = postsDao;
  }

  @PostMapping("/comments/{postId}")
  public String comment(@ModelAttribute Comment comment, @PathVariable long postId) {
    Post post = postsDao.findOne(postId);
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    comment.setOwner(user);
    comment.setPost(post);
    commentsDao.save(comment);
    return "redirect:/posts/" + postId;
  }

}
