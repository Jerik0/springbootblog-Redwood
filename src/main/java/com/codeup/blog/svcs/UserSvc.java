package com.codeup.blog.svcs;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.PostsRepository;
import com.codeup.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userSvc")
public class UserSvc {

  private final UserRepository usersDao;
  private final PostsRepository postsDao;

  @Autowired
  public UserSvc(UserRepository usersDao, PostsRepository postsDao) {
    this.usersDao = usersDao;
    this.postsDao = postsDao;
  }

  public boolean isPostOwner(Post post) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    long loggedInUserId = user.getId();
    return loggedInUserId == post.getOwner().getId();
  }

}
