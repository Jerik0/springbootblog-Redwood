package com.codeup.blog.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codeup.blog.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
