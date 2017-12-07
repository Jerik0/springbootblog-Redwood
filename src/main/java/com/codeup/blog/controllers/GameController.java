package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

  @GetMapping("/gamecenter")
  public String gameCenterHome() {
    return "/gamecenter/index";
  }

}
