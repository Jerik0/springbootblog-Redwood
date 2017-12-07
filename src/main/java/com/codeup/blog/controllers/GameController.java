package com.codeup.blog.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class GameController {

  @GetMapping("/gamecenter")
  public String gameCenterHome() {
    return "gamecenter/index";
  }

}
