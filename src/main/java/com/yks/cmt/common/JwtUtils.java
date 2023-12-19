package com.yks.cmt.common;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yks.cmt.dto.AdminVO;
import com.yks.cmt.dto.UserVO;
import com.yks.cmt.entity.WebUser;
import com.yks.cmt.exception.UnauthorizedException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtils implements HandlerInterceptor {
    private static String SECRET_KEY = "kason";
    private static final Integer EXPIRE_SECONDS = 60 * 60 * 24 * 1000;

    public static String createToken(UserVO userVO){

        Map<String,Object> map = new HashMap<>();
        map.put("alg","HMAC256");
        map.put("typ","JWT");
        Date nowDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_SECONDS);
        String token = JWT.create()
                .withHeader(map)
                .withClaim("username",userVO.getUserLoginName())
                .withJWTId(UUID.randomUUID().toString())
                .withIssuer("SERVICE")
                .withSubject("login token")
                .withAudience(String.valueOf(userVO.getUserId()))
                .withIssuedAt(nowDate)
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET_KEY));
        return token;
    }

    public static String createAdminToken(AdminVO adminVO){

        Map<String,Object> map = new HashMap<>();
        map.put("alg","HMAC256");
        map.put("typ","JWT");
        Date nowDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_SECONDS);
        String token = JWT.create()
                .withHeader(map)
                .withClaim("username",adminVO.getAdminName())
                .withJWTId(UUID.randomUUID().toString())
                .withIssuer("SERVICE")
                .withSubject("login token")
                .withAudience(String.valueOf(adminVO.getAdminId()))
                .withIssuedAt(nowDate)
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET_KEY));
        return token;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnauthorizedException {
        String token = request.getHeader("token");
        if(verify(token)){
            return true;
        }else {
            return false;
        }

    }

    public static Boolean verify(String token) throws UnauthorizedException {
        if (token==null){
            throw new UnauthorizedException();
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        DecodedJWT verify = null;
        try {
            verify = verifier.verify(token);
        } catch (Exception e) {
            throw new UnauthorizedException();
        }

        return true;
    }


}
