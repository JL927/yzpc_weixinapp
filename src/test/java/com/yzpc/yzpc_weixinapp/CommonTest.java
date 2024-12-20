package com.yzpc.yzpc_weixinapp;

import com.yzpc.yzpc_weixinapp.entity.Teacher;
import com.yzpc.yzpc_weixinapp.utils.JWTUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * @author wq
 * @description 工具测试
 * @date 2024/12/20 12:49:07
 */
public class CommonTest {

    @Test
    public void testJWT(){

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("data",new Teacher());

        String loginJWT = JWTUtils.generateJWT(claims);
        System.out.println(loginJWT);

        System.out.println(JWTUtils.parseJWT(loginJWT));
    }
}
