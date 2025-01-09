package com.yzpc.yzpc_weixinapp;

import com.luciad.imageio.webp.WebPEncoderOptions;
import com.yzpc.yzpc_weixinapp.entity.Teacher;
import com.yzpc.yzpc_weixinapp.entity.enums.Role;
import com.yzpc.yzpc_weixinapp.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author wq
 * @description 工具测试
 * @date 2024/12/20 12:49:07
 */
public class CommonTest {

    @Test
    public void testJWT(){

        HashMap<String, Object> claims = new HashMap<>();
        Teacher teacher =new Teacher();
        teacher.setRole("dean");
        claims.put("data",teacher);
        claims.put("role",teacher.getRole());

        String loginJWT = JWTUtils.generateJWT(claims);
        System.out.println(loginJWT);

        Claims claims1 = JWTUtils.parseJWT(loginJWT);

        System.out.println(claims1);
        System.out.println(JWTUtils.checkIsTeacher(claims1));
    }

    @Test
    public void testRole(){
//        System.out.println(Role.isRole("dean"));
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByMIMEType("image/webp");
        if (!writers.hasNext()) {
            System.err.println("No WebP ImageWriter found!");
        } else {
            System.out.println("WebP ImageWriter found!");
        };
    }
}
