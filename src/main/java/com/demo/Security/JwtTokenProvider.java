package com.demo.Security;



import java.sql.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "JWTSecertKey";  // Ensure this key is stored securely
    private static final long EXPIRATION_TIME = 3600000;  // 1 hour in milliseconds

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        Date currentDate = new Date(System.currentTimeMillis());
        Date expireDate = new Date(currentDate.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)  // Corrected this line
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Extract email from JWT token
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)  // Ensure this matches the key used to generate the token
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // Return the subject (email in this case)
    }
}


