package com.codeup.blog.svcs;

import com.codeup.blog.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("postSvc")
public class PostSvc {

  private final PostsRepository postsDao;

  @Autowired
  public PostSvc(PostsRepository postsDao) {
    this.postsDao = postsDao;
  }

}
