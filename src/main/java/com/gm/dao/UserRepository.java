package com.gm.dao;

import com.gm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by michael on 8/27/17.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
