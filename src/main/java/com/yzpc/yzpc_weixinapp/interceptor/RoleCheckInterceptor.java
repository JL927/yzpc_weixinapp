package com.yzpc.yzpc_weixinapp.interceptor;

import com.yzpc.yzpc_weixinapp.entity.enums.Role;
import com.yzpc.yzpc_weixinapp.exception.BusinessException;
import com.yzpc.yzpc_weixinapp.exception.ErrorCode;
import com.yzpc.yzpc_weixinapp.utils.JWTUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author wq
 * @description 检测登录身份
 * @date 2024/12/29 16:05:51
 */
@Slf4j
@Component
public class RoleCheckInterceptor implements HandlerInterceptor {

    @Resource
    public StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("身份校验");

        String msg = null;
        String token = request.getHeader("token");

        String role=stringRedisTemplate.opsForValue().get(token);
        if(role!=null){
            if (Role.isRole(role)){
                log.info("身份放行");
                return true;
            }else{
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"身份验证失败");
            }

        }

        try {

            Claims claims = JWTUtils.parseJWT(token);
            if (claims.containsKey("role")) {
                String jwt_role = (String) claims.get("role");
                if (Role.isRole(jwt_role)){
                    stringRedisTemplate.opsForValue().set(token,jwt_role,30, TimeUnit.MINUTES);
                    log.info("身份放行");
                    return true;
                }else{
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"身份验证失败");
                }
            }else{
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"身份信息丢失");
            }


        }catch (SignatureException se) {
            msg = "密钥错误";
            log.info(msg);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,msg);
        }catch (MalformedJwtException me) {

            msg = "密钥算法或者密钥转换错误";
            log.info(msg);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,msg);
        }catch (MissingClaimException mce) {

            msg = "密钥缺少校验数据";
            log.info(msg);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,msg);
        }catch (ExpiredJwtException mce) {

            msg = "密钥已过期";
            log.info(msg);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,msg);
        }catch (JwtException jwte) {

            msg = "密钥解析错误";
            log.info(msg);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,msg);
        }


//        log.info("身份放行");
//        return true;
    }
}
