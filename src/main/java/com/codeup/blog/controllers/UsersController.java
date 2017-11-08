package com.codeup.blog.controllers;

import com.codeup.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

  private final UserRepository usersDao;

  @Autowired
  public UsersController(UserRepository usersDao) {
    this.usersDao = usersDao;
  }

  @GetMapping("/users/create")
  public String createUser() {
    return "users/create";
  }

  @GetMapping("/users/profile")
  public String showProfile() {
    return "users/profile";
  }

}
