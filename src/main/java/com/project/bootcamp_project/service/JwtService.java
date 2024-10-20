package com.project.bootcamp_project.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtService {
    private final Key secretKey;

    public JwtService() {
        String JWT_SECRET_KEY = System.getenv("APP_JWT_SECRET_KEY");
        byte[] accessSecretBytes = Base64.getDecoder().decode(JWT_SECRET_KEY);
        this.secretKey = new SecretKeySpec(accessSecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String extractEmail(String token) {
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

        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();

    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateAccessToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email, secretKey, 5 * 60 * 1000);
    }

    public String generateRefreshToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email, secretKey, 24 * 60 * 60 * 1000);
    }

    private String createToken(Map<String, Object> claims, String subject, Key key, long expiresIn) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiresIn))
//                .setExpiration(new Date(System.currentTimeMillis() + 20000)) // for testing only 20 seconds
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public Boolean validateToken(String token, String email) {
        final String username = extractEmail(token);
        return (username.equals(email) && !isTokenExpired(token));
    }
}
