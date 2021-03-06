package com.codeup.blog.models;

import javax.persistence.*;
import java.util.List;

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

  @Column
  private String timestamp;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
  private List<Comment> comments;

  @Column()
  private Long votes;

  public Post() {

  }

  public Post(String title, String body, Long id, List<Comment> comments, String timestamp) {
    this.body = body;
    this.title = title;
    this.id = id;
    this.comments = comments;
    this.timestamp = timestamp;
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

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public long getVotes() {
    return votes;
  }

  public void setVotes(long votes) {
    this.votes = votes;
  }
}