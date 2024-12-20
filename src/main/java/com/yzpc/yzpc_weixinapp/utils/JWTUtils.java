package com.yzpc.yzpc_weixinapp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author wq
 * @description 处理登录校验令牌
 * @date 2024/12/20 13:14:00
 */
public class JWTUtils {

    private static final SecretKey key;

    static {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256);
            key = keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Long expire = 30 * 24 * 3600 * 1000L;


    public static String generateJWT(HashMap<String, Object> claims){

        String jwt = Jwts.builder()
                .claims(claims)
                .signWith(key)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expire))
                .compact();

        return jwt;
    }

    public static Claims parseJWT(String jwt){
        Claims claims = (Claims) Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(jwt)
                .getPayload();


        return claims;
    }
}
