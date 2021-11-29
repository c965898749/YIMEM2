package com.czx.yimem.service.impl;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
import com.czx.yimem.entity.Member;
import com.czx.yimem.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class PcTokenService {
    public String getToken(Member member) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(member.getAccount()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(member.getPassword()));
        return token;
    }
}
