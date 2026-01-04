package com.springTutorial.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springTutorial.libraryManagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
