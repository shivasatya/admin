package com.example.userManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userManagement.entity.RefreshToken;


@Repository
public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Integer> {

}
