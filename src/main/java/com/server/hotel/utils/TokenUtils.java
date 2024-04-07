package com.server.hotel.utils;

import com.server.hotel.entry.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private final static String SECRET = "YUZHICHENG";

    public static String getToken(UserInfo user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setExpiration(new Date(new Date().getTime()+2419200000L));
        return jwtBuilder.compact();

    }

    public static String verify(String token){
        token = token.substring(7);
        Claims claims = Jwts.parser().
                setSigningKey(SECRET).
                parseClaimsJws(token).
                getBody();
        return (String) claims.get("id");
    }
}
