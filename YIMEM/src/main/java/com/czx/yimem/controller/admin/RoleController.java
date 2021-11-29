package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dto.AuthorityOV;
import com.czx.yimem.dto.AuthorityVO;
import com.czx.yimem.dto.RoleDTO;
import com.czx.yimem.dto.RoleVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description : PC 端角色相关接口
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页查找
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_AM_JS_CXJS','ROLE_ADMIN')")
    @PostMapping("/search")
    public BaseResp<Page<RoleVO>> get(@RequestBody RoleDTO dto){
        return roleService.search(dto);
    }


    /**
     * 新建角色
     */
    @SysLog("新建角色")
    @PreAuthorize("hasAnyRole('ROLE_AM_JS_XJJS','ROLE_ADMIN')")
    @PostMapping("/create")
    public BaseResp<RoleVO> create(@RequestBody RoleVO vo){
        return roleService.create(vo);
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @PreAuthorize("hasAnyRole('ROLE_AM_JS_XGJS','ROLE_ADMIN')")
    @PostMapping("/update")
    public BaseResp<RoleVO> update(@RequestBody RoleVO vo){
        return roleService.update(vo);
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @PreAuthorize("hasAnyRole('ROLE_AM_JS_SCJS','ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public BaseResp delete(@PathVariable()int id){
        return roleService.delete(id);
    }


    //获取某角色的权限列表
    @SysLog("获取某角色的权限列表")
    @PreAuthorize("hasAnyRole('ROLE_AM_JS_CXJSQL','ROLE_ADMIN')")
    @PostMapping("/authority/get")
    public BaseResp<List<AuthorityVO>> getAuthority(@RequestBody RoleVO role){

        return roleService.getAuthority(role.getId());
    }


    //设置某角色的权限
    @SysLog("设置某角色的权限")
    @PreAuthorize("hasAnyRole('ROLE_AM_JS_SZJSQL','ROLE_ADMIN')")
    @PostMapping("/authority/set/{id}")
    public BaseResp updateAuthority(@PathVariable int id,@RequestBody AuthorityOV authes){

        return roleService.setAuth(id,authes);
    }

}
