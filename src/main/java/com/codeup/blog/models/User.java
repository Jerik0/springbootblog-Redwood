package com.codeup.blog.models;

import javax.persistence.*;
import java.util.List;

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

  @Column(nullable = false)
  private String password;

  @Column()
  private String imagePath;

  @Column()
  private String bio;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  private List<Post> posts;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  private List<Comment> comments;

  public User() {

  }

  public User(User copy) {
    id = copy.id;
    username = copy.username;
    email = copy.email;
    password = copy.password;
    posts = copy.posts;
    comments = copy.comments;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

}
