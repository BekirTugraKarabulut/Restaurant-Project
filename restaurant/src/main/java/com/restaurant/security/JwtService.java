package com.restaurant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${secret-key}")
    public static String secretKey;

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 24 * 60 * 60 * 1000))
                .signWith(getKey() , SignatureAlgorithm.HS256)
                .compact();

    }

    public Claims getClaims(String token){

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;

    }

    public <T> T exportToken(String token , Function<Claims, T> claimsTFunction){

        Claims claims = getClaims(token);
        return claimsTFunction.apply(claims);

    }

    public String getUsernameByToken(String token){
        return exportToken(token , Claims::getSubject);
    }

    public boolean isValidToken(String token){
        return new Date().before(getClaims(token).getExpiration());
    }

    public Key getKey(){
        byte[] encodedKey = Base64.getEncoder().encode(secretKey.getBytes());
        return Keys.hmacShaKeyFor(encodedKey);
    }

}
