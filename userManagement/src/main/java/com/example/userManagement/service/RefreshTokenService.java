package com.example.userManagement.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userManagement.entity.RefreshToken;
import com.example.userManagement.repository.RefreshTokenRepository;
import com.example.userManagement.repository.UserRepository;

@Service
public class RefreshTokenService {


	@Autowired
	private RefreshTokenRepository refreshTokRepository;

	@Autowired
	private UserRepository userRepository;


	public String createRefreshToken(String userName) {
		RefreshToken reToken = RefreshToken.builder().usereEntity(userRepository.findByUserName(userName).get())
				.token(UUID.randomUUID().toString()).expDate(Instant.now().plusMillis(600000)).build();
		refreshTokRepository.save(reToken);
		return "REFRESH TOKEN SAVED";

	}
}
