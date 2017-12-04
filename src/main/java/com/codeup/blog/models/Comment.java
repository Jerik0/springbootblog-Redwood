package com.codeup.blog.models;

import javax.persistence.*;

@Entity
@Table
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String body;

  @Column
  private String timestamp;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  @Column()
  private long votes;

  public Comment() {

  }

  public Comment(String body, String timestamp, User owner, Post post) {
    this.body = body;
    this.timestamp = timestamp;
    this.owner = owner;
    this.post = post;
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

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
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
