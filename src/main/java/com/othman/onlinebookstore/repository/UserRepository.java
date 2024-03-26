package com.othman.onlinebookstore.repository;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;

import com.othman.onlinebookstore.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    
    public Optional<UserEntity> findUserByEmail(String email);
    public boolean existsByEmail(String email);
}
