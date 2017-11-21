package com.codeup.blog.controllers;

import com.codeup.blog.models.Comment;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.CommentsRepository;
import com.codeup.blog.repositories.PostsRepository;
import com.codeup.blog.repositories.UserRepository;
import com.codeup.blog.svcs.PostSvc;
import com.codeup.blog.svcs.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Controller
public class PostsController {

  private final PostSvc postSvc;
  private final PostsRepository postsDao;
  private final UserRepository usersDao;
  private final UserSvc userSvc;
  private final CommentsRepository commentsDao;

  @Autowired
  public PostsController(PostSvc postSvc, PostsRepository postsDao, UserRepository usersDao, UserSvc userSvc, CommentsRepository commentsDao) {
    this.postSvc = postSvc;
    this.postsDao = postsDao;
    this.usersDao = usersDao;
    this.userSvc = userSvc;
    this.commentsDao = commentsDao;
  }

  //============CREATION OF POSTS=============
  @GetMapping("/posts/create")
  public String showCreateForm(Model model) {
    model.addAttribute("post", new Post());
    return "/posts/create";
  }

  @PostMapping("/posts/create")
  public String postsCreate(@ModelAttribute Post post) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Instant instant = Instant.now();
    DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.US)
            .withZone(ZoneId.systemDefault());
    String timestamp = formatter.format(instant);
    post.setOwner(user);
    post.setTimestamp(timestamp);
    postsDao.save(post);
    return "redirect:/posts";
  }

  //=============READING OF POSTS=============
  @GetMapping("/posts")
  public String posts(Model model) {
    model.addAttribute("posts", postsDao.findAll());
    return "/posts/index";
  }

  @GetMapping("/posts/{id}")
  public String postsId(@PathVariable Integer id, Model model) {
    Post post = postsDao.findOne((long) id);
    boolean isPostOwner = userSvc.isPostOwner(post);
    boolean isLoggedIn = userSvc.isLoggedIn();
    model.addAttribute("isPostOwner", isPostOwner);
    model.addAttribute("isLoggedIn", isLoggedIn);
    model.addAttribute("post", post);
    model.addAttribute("comment", new Comment());
    model.addAttribute("user", usersDao.findOne((long) id));
    model.addAttribute("comments", commentsDao.findAllById((long) id));
    return "/posts/show";
  }

  //=============UPDATING OF POSTS=============
  @GetMapping("/posts/{id}/edit")
  public String editPost(@PathVariable Integer id, Model model) {
    Post post = postsDao.findOne((long) id);
    boolean isPostOwner = userSvc.isPostOwner(post);
    model.addAttribute("post", post);
    model.addAttribute("isPostOwner", isPostOwner);
    return "/posts/edit";
  }

  @PostMapping("/posts/{id}/edit")
  public String saveEditedPost(@PathVariable Integer id, @ModelAttribute Post editedPost) {
    Post oldPost = postsDao.findOne((long) id);
    oldPost.setTitle(editedPost.getTitle());
    oldPost.setBody(editedPost.getBody());
    postsDao.save(oldPost);
    return "redirect:/posts";
  }

  //============DELETION OF POSTS==============
  @PostMapping("/posts/{id}/delete")
  public String deletePost(@PathVariable Integer id) {
    postsDao.delete((long) id);
    return "redirect:/posts";
  }

}
