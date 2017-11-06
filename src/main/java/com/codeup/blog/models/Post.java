package com.codeup.blog.models;

public class Post {

  private Long id;
  private String body;
  private String title;

  public Post() {

  }

  public Post(String title, String body, Long id) {
    this.body = body;
    this.title = title;
    this.id = id;
  }

  public Post(String title, String body) {
    this.title = title;
    this.body = body;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}