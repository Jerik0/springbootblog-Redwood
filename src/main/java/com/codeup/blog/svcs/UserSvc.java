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
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(principal instanceof String) {
      return false;
    }
    long ownerId = post.getOwner().getId();
    return ((User) principal).getId() == ownerId;
  }

  public boolean isLoggedIn() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return principal instanceof User;
  }

  public long getLoggedInUserId() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(principal instanceof String) {
      return 0;
    }
    return ((User) principal).getId();
  }

  public User getLoggedInUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(principal instanceof String) {
      return null;
    }
    long id = getLoggedInUserId();
    return usersDao.findOne(id);
  }
}
