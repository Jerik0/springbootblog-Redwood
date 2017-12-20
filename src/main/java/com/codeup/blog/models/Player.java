package com.codeup.blog.models;

import javax.persistence.*;

@Entity
@Table
public class Player {

  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private long highScore;

  @Column(nullable = false, length = 40)
  private String playername;

  public Player() {

  }

  public Player(Long id, long highScore, String playername) {
    this.id = id;
    this.highScore = highScore;
    this.playername = playername;
  }

  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getHighScore() {
    return highScore;
  }

  public void setHighScore(long highScore) {
    this.highScore = highScore;
  }

  public String getPlayername() {
    return playername;
  }

  public void setPlayername(String playername) {
    this.playername = playername;
  }
}
