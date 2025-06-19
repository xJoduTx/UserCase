package com.apiVerse.UserCase.repository;


import com.apiVerse.UserCase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Boolean existsUserByUsername(String username);
    Boolean existsUserByPhoneNumber(String phoneNumber);
}
