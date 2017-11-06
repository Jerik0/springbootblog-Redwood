package com.codeup.blog.svcs;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("postSvc")
public class PostSvc {

  private List<Post> posts;

  public PostSvc() {
    this.posts = new ArrayList<>();
    create();
  }

  public List<Post> findAll() {
    return posts;
  }

  public Post save(Post post) {
    post.setId((long) (posts.size() + 1));
    posts.add(post);
    return post;
  }

  public Post findOne(long id) {
    return posts.get((int) (id - 1));
  }

  public void create() {
    save(new Post("Creative Title", "My body is ready"));
    save(new Post("Hardcoding YAY", "code my body"));
    save(new Post("Yummy Donuts", "Candy candy candy candy candy candy candy coffee candy"));
  }

}
