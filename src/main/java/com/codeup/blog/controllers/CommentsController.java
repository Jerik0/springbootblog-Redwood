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

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

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
    Instant instant = Instant.now();
    DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                    .withLocale(Locale.US)
                    .withZone(ZoneId.systemDefault());
    String timestamp = formatter.format(instant);
    Post post = postsDao.findOne(postId);
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    comment.setTimestamp(timestamp);
    comment.setOwner(user);
    comment.setPost(post);
    commentsDao.save(comment);
    return "redirect:/posts/" + postId;
  }

  @PostMapping("/comments/{commentId}/delete/{postId}")
  public String deleteComment(@PathVariable Integer commentId, @PathVariable Integer postId) {
    commentsDao.delete((long) commentId);
    return "redirect:/posts/" + postId;
  }

}
