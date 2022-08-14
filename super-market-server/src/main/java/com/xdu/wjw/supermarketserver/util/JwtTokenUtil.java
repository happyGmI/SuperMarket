package com.xdu.wjw.supermarketserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserReq;
import com.xdu.wjw.supermarketserver.invoke.user.model.UserResp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Class: JwtTokenUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/14 17:23
 * @Description:
 */
public class JwtTokenUtil {
    //过期时间设置
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    // 私钥设置
    private static final String TOKEN_SECRET = "5xcJVrXNyQDIxK1l2RS9nw";

    public static String getToken(UserReq userReq){
        //过期时间和加密算法设置
        Date date=new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm =Algorithm.HMAC256(TOKEN_SECRET);

        //头部信息
        Map<String,Object> header=new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim("autograph" ,userReq.getAutograph())
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static UserResp getTokenData(String token){
        DecodedJWT jwt = JWT.decode(token);
        return UserResp.builder().build();
    }
}