package com.springtest.testconnect.repository;

import com.springtest.testconnect.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByUnitId(Long unitId);
    List<User>findByFnameContainingIgnoreCase(String fname);
}
