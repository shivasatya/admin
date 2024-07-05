package com.example.userManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userManagement.entity.UsereEntity;

@Repository
public interface UserRepository  extends JpaRepository<UsereEntity, String>{

	Optional<UsereEntity> findByEmailAndRoles(String email, String userRole);

	Optional<UsereEntity> findByUserName(String name);

	Optional<UsereEntity> findByEmail(String email);

}
