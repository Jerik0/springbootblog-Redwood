package com.codeup.blog.models;

import javax.persistence.*;

@Entity
@Table
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false)
  private String body;

  @ManyToOne
  private User owner;

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

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}