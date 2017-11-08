package com.codeup.blog.models;

import javax.persistence.*;

@Entity
@Table
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;


  @Column(nullable = false, unique = true)
  private String email;

  public User() {

  }

  public User(User copy) {
    id = copy.id;
    username = copy.username;
    email = copy.email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


}
