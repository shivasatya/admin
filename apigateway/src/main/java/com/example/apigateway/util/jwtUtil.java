package com.example.apigateway.util;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtUtil {
	private static final String key ="1c11f62a210d58f0aa2f826f4e105def9fa24fd20dc09b3628a3dfc32374995e";


	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(key);
				return Keys.hmacShaKeyFor(keyBytes);
	}
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validToken(String token) {
        final String username = extractUsername(token);
        return (username.equals(username) && !isTokenExpired(token));
    }
	


	public List extractRoles(String token) {
		Claims claims = extractAllClaims(token);
		if (claims != null && claims.containsKey("ROLES")) {
		return claims.get("ROLES",List.class);
		}
		return Collections.EMPTY_LIST; // Roles not found or token is invalid
	}
}
