package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findByEmail(String email);
}
