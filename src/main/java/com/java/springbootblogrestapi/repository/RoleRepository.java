package com.java.springbootblogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springbootblogrestapi.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
