package com.demo03.demo03.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo03.demo03.model.testModel;

public interface testRepository extends JpaRepository<testModel, String> {
    Optional<testModel> findByEmail(String email);
}
