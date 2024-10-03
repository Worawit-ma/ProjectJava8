package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
@Service
public class TokenGenerator {
    String key = "99c7d535-2855-485d-9e0d-e143bd525da6";

    public String generateToken() {
        JSONObject UserInfo = new JSONObject();
        UserInfo.put("user", "userName1")
                .put("Role", "superAdmin")
                .put("status", "Active");

        JwtBuilder builder = Jwts.builder()
        .setSubject(UserInfo.toString())
        .setIssuedAt(new Date())
        .signWith(SignatureAlgorithm.HS256, key);

        return builder.compact();
    }

    public String generateTokenWithText(String dataInput) {
        JwtBuilder builder = Jwts.builder()
        .setSubject(dataInput)
        .setIssuedAt(new Date())
        .signWith(SignatureAlgorithm.HS256, key);

        return builder.compact();
    }

    public String decodeToken(String dataInput) {
               Claims claims = Jwts.parser()
                            .setSigningKey(key)
                            .parseClaimsJws(dataInput)
                            .getBody();

        String subject = claims.getSubject(); 
        Date issuedAt = claims.getIssuedAt();  
        String customClaim = (String) claims.get("customClaimKey");
        JSONObject result = new JSONObject();

        result.put("Subject", subject).put("Issued at", new java.sql.Date(issuedAt.getTime())).put("Custom Claim", customClaim);
        return result.toString();
    }

}
