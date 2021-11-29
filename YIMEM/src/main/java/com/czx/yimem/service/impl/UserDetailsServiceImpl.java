package com.czx.yimem.service.impl;

import com.czx.yimem.entity.JwtUser;
import com.czx.yimem.entity.Member;
import com.czx.yimem.service.admin.MembserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 * @description UserDetailsService实现类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MembserService membserService;

    public UserDetailsServiceImpl(MembserService membserService) throws UsernameNotFoundException{
        this.membserService = membserService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) {

        Member user = membserService.findByMember(name);
        if(user == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new JwtUser(user);
    }

}
