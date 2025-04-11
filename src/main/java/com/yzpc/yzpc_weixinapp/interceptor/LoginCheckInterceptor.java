package com.yzpc.yzpc_weixinapp.interceptor;

import com.yzpc.yzpc_weixinapp.exception.BusinessException;
import com.yzpc.yzpc_weixinapp.exception.ErrorCode;
import com.yzpc.yzpc_weixinapp.utils.JWTUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author wq
 * @description 检查登录
 * @date 2024/12/20 13:27:43
 */
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Resource
    public StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("登录校验");
//        String requestUri = request.getRequestURI();
//        System.out.println("拦截器处理请求: " + requestUri);

        String msg = null;
        String token = request.getHeader("token");

        String role=stringRedisTemplate.opsForValue().get(token);
        if(role!=null){
            log.info("登录放行");
            return true;
        }

        try {

            Claims claims = JWTUtils.parseJWT(token);
            if (claims.containsKey("role")) {
                String jwt_role = (String) claims.get("role");
                stringRedisTemplate.opsForValue().set(token,jwt_role,30, TimeUnit.MINUTES);
            }


        }catch (SignatureException se) {
            msg = "密钥错误";
            log.info(msg);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,msg);
//            return false;
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


        log.info("登录放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
