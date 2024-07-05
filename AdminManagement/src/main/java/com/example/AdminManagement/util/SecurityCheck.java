package com.example.AdminManagement.util;

import java.security.Key;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class SecurityCheck {

	@Autowired
	HttpServletRequest httpServletRequest;

	private static final String key ="1c11f62a210d58f0aa2f826f4e105def9fa24fd20dc09b3628a3dfc32374995e";


	public  List<String> validToken(){
		return getTokenCheck(httpServletRequest);

	}

	public String getToken() {
		return getHttpToken(httpServletRequest);
	}
	public String getHttpToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	public List<String> getTokenCheck(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
		String tokenData =	bearerToken.substring(7, bearerToken.length());
		List<String> data =	extractRoles(tokenData);
			return data;
		}
		return null;
	}


	public List extractRoles(String token) {
		Claims claims = extractAllClaims(token);
		if (claims != null && claims.containsKey("ROLES")) {
		return claims.get("ROLES",List.class);
		}
		return Collections.EMPTY_LIST; // Roles not found or token is invalid
	}
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(key);
				return Keys.hmacShaKeyFor(keyBytes);
	}
}
