package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

  @GetMapping("/add/{num1}/and/{num2}")
  @ResponseBody
  public Integer addNumbers(@PathVariable Integer num1, @PathVariable Integer num2) {
    return (num1 + num2);
  }

  @GetMapping("/subtract/{num1}/from/{num2}")
  @ResponseBody
  public Integer subtractNumbers(@PathVariable Integer num1, @PathVariable Integer num2) {
    return (num2 - num1);
  }

  @GetMapping("/multiply/{num1}/by/{num2}")
  @ResponseBody
  public Integer multiplyNumbers(@PathVariable Integer num1, @PathVariable Integer num2) {
    return (num1 * num2);
  }

  @GetMapping("/divide/{num1}/by/{num2}")
  @ResponseBody
  public Double divideNumbers(@PathVariable Double num1, @PathVariable Double num2) {
    return (num1 / num2);
  }
}
