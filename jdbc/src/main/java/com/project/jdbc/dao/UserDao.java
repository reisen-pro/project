package com.project.jdbc.dao;

import com.project.jdbc.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Reisen
 */

public interface UserDao extends CrudRepository<User, Long> {
}
