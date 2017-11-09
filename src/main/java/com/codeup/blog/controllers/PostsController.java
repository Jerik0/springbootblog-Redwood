package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.PostsRepository;
import com.codeup.blog.repositories.UserRepository;
import com.codeup.blog.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostsController {

  private final PostSvc postSvc;
  private final PostsRepository postsDao;
  private final UserRepository usersDao;

  @Autowired
  public PostsController(PostSvc postSvc, PostsRepository postsDao, UserRepository usersDao) {
    this.postSvc = postSvc;
    this.postsDao = postsDao;
    this.usersDao = usersDao;
  }

  @GetMapping("/posts")
  public String posts(Model model) {
    model.addAttribute("posts", postsDao.findAll());
    return "/posts/index";
  }

  @GetMapping("/posts/{id}")
  public String postsId(@PathVariable Integer id, Model model) {
    Post post = postsDao.findOne((long) id);
    model.addAttribute("post", post);
    return "/posts/show";
  }

  @GetMapping("/posts/{id}/edit")
  public String editPost(@PathVariable Integer id, Model model) {
    model.addAttribute("post", postsDao.findOne((long) id));
    return "posts/edit";
  }

  @PostMapping("/posts/{id}/edit")
  public String saveEditedPost(@PathVariable Integer id, @ModelAttribute Post editedPost) {
    Post oldPost = postsDao.findOne((long) id);
    oldPost.setTitle(editedPost.getTitle());
    oldPost.setBody(editedPost.getBody());
    postsDao.save(oldPost);
    return "redirect:/posts";
  }

  @GetMapping("/posts/create")
  public String showCreateForm(Model model) {
    model.addAttribute("post", new Post());
    return "posts/create";
  }

  @PostMapping("/posts/create")
  public String postsCreate(@ModelAttribute Post post) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    postsDao.save(post);
    post.setOwner(user);
    return "redirect:/posts";
  }

  @PostMapping("/posts/{id}/delete")
  public String deletePost(@PathVariable Integer id) {
    postsDao.delete((long) id);
    return "redirect:/posts";
  }

}
