package com.yzpc.yzpc_weixinapp.interceptor;

import com.yzpc.yzpc_weixinapp.utils.JWTUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wq
 * @description 检查登录
 * @date 2024/12/20 13:27:43
 */
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("登录校验");

        String msg = null;
        String token = request.getHeader("token");

        try {

            Claims claims = JWTUtils.parseJWT(token);

        }catch (SignatureException se) {
            msg = "密钥错误";
            log.info(msg);
            return false;
        }catch (MalformedJwtException me) {

            msg = "密钥算法或者密钥转换错误";
            log.info(msg);
            return false;
        }catch (MissingClaimException mce) {

            msg = "密钥缺少校验数据";
            log.info(msg);
            return false;
        }catch (ExpiredJwtException mce) {

            msg = "密钥已过期";
            log.info(msg);
            return false;
        }catch (JwtException jwte) {

            msg = "密钥解析错误";
            log.info(msg);
            return false;
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
