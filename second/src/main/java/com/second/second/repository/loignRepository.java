package com.second.second.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.second.second.model.loginUser;
import java.util.Optional;

public interface loignRepository extends JpaRepository<loginUser, String> {

    Optional<loginUser> findByEmail(String email);
}
