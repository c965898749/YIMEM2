package com.czx.yimem.service.impl;

import com.czx.yimem.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/***
 * token 下发
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class TokenService {

    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 12*60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getUserId()+"").withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getOpenid()));
        return token;
    }
}


