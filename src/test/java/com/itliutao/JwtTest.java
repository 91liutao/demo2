package com.itliutao;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testJwt() {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256,"aXRsaXV0YW8=")
                .addClaims(dataMap).setExpiration(new Date(System.currentTimeMillis()+1000*60*60*7))
                .compact();//指定加密算法，密钥
        System.out.println(jwt);
    }
    @Test
    public void testParseJwt() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0Njk5OTg2NH0.AJYHuHtIJaABCajGO-4YbgNzFKBg2iXFo4TgHJeWpqM";
        Claims claims = Jwts.parser().setSigningKey("aXRsaXV0YW8=")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
