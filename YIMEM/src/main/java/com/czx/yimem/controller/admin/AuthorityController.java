package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dto.AuthorityVO;
import com.czx.yimem.dto.MenuVO;
import com.czx.yimem.dto.MenuVO2;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.AuthorityService;
import com.czx.yimem.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/admin/authority")
public class AuthorityController {

@Autowired
private AuthorityService authorityService;

private Logger logger= LoggerFactory.getLogger(this.getClass());



    /**
     *获取当前用户的授权的菜单和操作列表,登录完成后可以请求这个接口
     * @return
     */
    @GetMapping("/mine")
    public BaseResp get(){
        if(SecurityContextHolder.getContext()
                .getAuthentication().isAuthenticated()){
            String account= SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal().toString();
            return authorityService.getUserAuthority(account);
        }else {
            return null;
        }

    }
    @SysLog("管理员登录记录")
    @GetMapping("/menu")
    public BaseResp getMenu(){
        if(SecurityContextHolder.getContext()
                .getAuthentication().isAuthenticated()){
            String account= SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal().toString();
            return authorityService.getUserMenu(account);
        }else {
            return null;
        }
    }


    /**
     * 获取所有权限编码
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_SYQX','ROLE_ADMIN')")
    public BaseResp getAll(){
        return authorityService.getAll();
    }






}
