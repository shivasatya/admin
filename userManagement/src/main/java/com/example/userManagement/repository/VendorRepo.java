package com.example.userManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userManagement.entity.VendorMessageEntity;

@Repository
public interface VendorRepo extends JpaRepository<VendorMessageEntity, Integer>{

	List<VendorMessageEntity> findByEmail(String email);


}
