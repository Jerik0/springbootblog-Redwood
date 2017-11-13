package com.codeup.blog.controllers;

import com.codeup.blog.models.User;
import com.codeup.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

  private final UserRepository usersDao;
  private final PasswordEncoder encoder;

  @Autowired
  public UsersController(UserRepository usersDao, PasswordEncoder encoder) {
    this.usersDao = usersDao;
    this.encoder = encoder;
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
  public String showProfile() {
    return "users/profile";
  }

}
