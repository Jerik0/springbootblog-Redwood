package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceRollController {

  @GetMapping("/roll-dice/{n}")
  public String randomizer(@PathVariable Integer n, Model model) {
    int picked = (int) Math.floor((Math.random() * 6) + 1);
    String helper;
    boolean notEqual;
    if(n > picked) {
      helper = "High";
      notEqual = true;
      model.addAttribute("helper", helper);
    } else if(n < picked) {
      helper = "Low";
      notEqual = true;
      model.addAttribute("helper", helper);
    } else {
      notEqual = false;
    }
    model.addAttribute("roll", n);
    model.addAttribute("notEqual", notEqual);
    return "rollresults";
  }

  @GetMapping("/roll-dice")
  public String diceRollHome() {
    return "rolldice";
  }
}
