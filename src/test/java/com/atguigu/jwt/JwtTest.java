package com.atguigu.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class JwtTest {
    //过期时间，毫秒，30分钟
    private static long tokenExpiration=1000;
    //秘钥
    private static String tokenSignKey="haha123456";
    @Test
    public void testCreateToken(){
        String token = Jwts.builder().setHeaderParam("typ", "JWT")//令牌类型
                .setHeaderParam("alg", "HS256")//签名算法
                .setSubject("guli-user")//令牌主题
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))//过期时间
                .claim("nickname", "李晗")
                .claim("avatar", "1.jpg")
                .signWith(SignatureAlgorithm.HS256, tokenSignKey)//签名哈希
                .compact();//转换成字符串
        System.err.println(token);
    }
    @Test
    public void testGetUserInfo(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWxpLXVzZXIiLCJleHAiOjE2MDI1NTg2NDcsIm5pY2tuYW1lIjoi5p2O5pmXIiwiYXZhdGFyIjoiMS5qcGcifQ.PxKgVbZ7xvx9VeHRSM10SuOqKiKbSbHUenjNL99IeKY";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        String nickname = (String)body.get("nickname");
        String avatar = (String) body.get("avatar");
        System.err.println(nickname);
        System.out.println(avatar);
    }
}