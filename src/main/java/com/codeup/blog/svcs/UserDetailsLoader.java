package com.codeup.blog.svcs;

import com.codeup.blog.models.User;
import com.codeup.blog.models.UserWithRoles;
import com.codeup.blog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsLoader implements UserDetailsService{

  private UserRepository repository;

  public UserDetailsLoader(UserRepository repository) {
    this.repository = repository;

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = repository.findByUsername(username);
    if(user == null) {
      throw new UsernameNotFoundException("No user found for " + username);
    }
    return new UserWithRoles(user, Collections.emptyList());
  }

}
