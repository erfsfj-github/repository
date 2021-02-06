package com.gcase.feat.interceptors;

import com.erfsfj.middleware.redis.constant.RedisTime;
import com.erfsfj.utils.http.HttpKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
//<dependency>
//    <groupId>org.springframework.boot</groupId>
//    <artifactId>spring-boot-starter-data-redis</artifactId>
//    <version>2.1.17.RELEASE</version>
//</dependency>
/**
 * <p>
 *
 * <p>
 *
 * @creator bicheng.deng
 * @createTime 2021/2/1
 */

public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String pageToken = request.getHeader(HttpKey.USER_TOKEN);
        String key = request.getHeader(HttpKey.USER_KEY);
        String redisToken = redisTemplate.opsForValue().get(key);
        if(pageToken.equals(redisToken)){
            redisTemplate.opsForValue().set(key,redisToken, RedisTime.TOKEN_TIMEOUT, TimeUnit.MINUTES );
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
