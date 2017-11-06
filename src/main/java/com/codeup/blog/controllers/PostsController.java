package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostsController {

  private final PostSvc postSvc;

  @Autowired
  public PostsController(PostSvc postSvc) {
    this.postSvc = postSvc;
  }

  @GetMapping("/posts")
  public String posts(Model model) {
    model.addAttribute("posts", postSvc.findAll());
    return "/posts/index";
  }

  @GetMapping("/posts/{id}")
  public String postsId(@PathVariable Integer id, Model model) {
    Post post = postSvc.findOne((long) id);
    model.addAttribute("post", post);
    return "/posts/show";
  }

  @GetMapping("/posts/create")
  public String showCreateForm(Model model) {
    model.addAttribute("post", new Post());
    return "posts/create";
  }

  @PostMapping("/posts/create")
  public String postsCreate(@ModelAttribute Post post) {
    postSvc.save(post);
    return "redirect:/posts";
  }

}
