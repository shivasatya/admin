package com.example.AdminManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AdminManagement.entity.AdminEntity;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity, String>{

	AdminEntity findByEmail(String email);

}
