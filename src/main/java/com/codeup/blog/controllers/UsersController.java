package com.codeup.blog.controllers;

import com.codeup.blog.models.User;
import com.codeup.blog.repositories.UserRepository;
import com.codeup.blog.svcs.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

  private final UserRepository usersDao;
  private final PasswordEncoder encoder;
  private final UserSvc usersSvc;

  @Autowired
  public UsersController(UserRepository usersDao, PasswordEncoder encoder, UserSvc userSvc) {
    this.usersDao = usersDao;
    this.encoder = encoder;
    this.usersSvc = userSvc;
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "users/create";
  }

  @PostMapping("/register")
  public String saveUser(@ModelAttribute User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    usersDao.save(user);
    return "redirect:/login";
  }

  @GetMapping("/profile")
  public String showProfile(Model model) {
    User user = usersSvc.getLoggedInUser();
    model.addAttribute("user", user);
    return "users/profile";
  }

  @GetMapping("/user/{id}/view")
  public String showUserPosts(@PathVariable Integer id, Model model) {
    User user = usersSvc.getLoggedInUser();
    model.addAttribute("user", user);
    return "/posts/userposts";
  }

  @PostMapping("/upload")
  public String uploadImage(@RequestParam("imageUrl") String imageUrl, Model model) {
    User user = usersSvc.getLoggedInUser();
    user.setImagePath(imageUrl);
    usersDao.save(user);
    model.addAttribute("imageUrl");
    return "redirect:/profile";
  }

  @PostMapping("/image/delete")
  public String deleteImage() {
    User user = usersSvc.getLoggedInUser();
    user.setImagePath(null);
    usersDao.save(user);
    return "redirect:/profile";
  }

}
