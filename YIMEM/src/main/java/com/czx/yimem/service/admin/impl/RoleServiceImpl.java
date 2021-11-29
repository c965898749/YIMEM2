package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.RcRoleAuthorityMapper;
import com.czx.yimem.dto.*;
import com.czx.yimem.dao.AuthorityMapper;
import com.czx.yimem.dao.RoleAuthorityMapper;
import com.czx.yimem.dao.RoleMapper;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.RoleService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.StringUtils;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Resource
    private RoleMapper roleMapper;

    @Resource
    private AuthorityMapper authorityMapper;

    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;

    @Resource
    private RcRoleAuthorityMapper rcRoleAuthorityMapper;

    @Override
    public BaseResp search(RoleDTO dto) {
        try {
            PageVO pageVO = JSON.toJavaObject(dto.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            List<Role> ms = roleMapper.search(dto);
            Page<Role> jobPage = (Page<Role>) ms;
            Map map = new HashMap();
            map.put("content", ms);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp create(RoleVO vo) {
        try {
            Role role = new Role();
            BeanUtils.copyProperties(vo, role);
            role.setState("1");
            roleMapper.insert(role);
            vo.setId(role.getId());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, vo);
        } catch (BeansException e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp update(RoleVO vo) {
        try {
            Role record = roleMapper.selectByPrimaryKey(vo.getId());
            if (record != null) {
                record.setName(vo.getName());
                record.setRemark(vo.getRemark());
                roleMapper.updateByPrimaryKeySelective(record);

                RoleVO rt = new RoleVO();
                BeanUtils.copyProperties(record, rt);
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
            } else {
                return new BaseResp(Constants.FAIL_CODE, Constants.NOT_FOUND);
            }
        } catch (BeansException e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }

    }

    @Override
    public BaseResp getAuthority(Integer roleId) {
        try {
            Map map=new HashMap();
            List<Integer> menusid=new ArrayList<>();
            List<RcRoleAuthority> authes = rcRoleAuthorityMapper.selectByRoleId(roleId);
            if (Xtool.isNull(authes)) {
                map.put("menusid",menusid);
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
            }
            authes.forEach(x->{
                menusid.add(x.getAuthorityId());
            });
            map.put("menusid",menusid);
//            authes.stream().map(a -> {
//                AuthorityVO vo = new AuthorityVO();
//                BeanUtils.copyProperties(a, vo);
//                return vo;
//            }).collect(Collectors.toList());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }

    }

    @Override
    public BaseResp setAuth(int id, AuthorityOV authorityOV) {
        try {
            List<Integer> authes=authorityOV.getMenusid();
                //删除原有的
                roleAuthorityMapper.deleteByRoleid(id);
                //根据输入的权限code，从权限表中获取权限，如果传入的有不匹配的则发生异常
//                List<Authority> auth = authorityMapper.selectByAuth(authes);
//                if (auth.size() != authes.size()) {
//                    return new BaseResp(Constants.FAIL_CODE, "权限代码不匹配");
//                }
                authes.forEach(a -> {
                    RoleAuthority record = new RoleAuthority();
                    record.setAuthorityId(a);
                    record.setRoleId(id);
                    roleAuthorityMapper.insert(record);
                });
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public List<Role> selectByMemberId(Integer mid) {
        return Xtool.isNotNull(mid) ? roleMapper.selectByMemberId(mid) : null;
    }

    @Override
    public BaseResp delete(Integer id) {
        try {
//        删角色
            roleMapper.deleteByPrimaryKey(id);
//        再删角色权限
            roleAuthorityMapper.deleteByRoleid(id);
//            最后把原工身上的角色删掉
            roleMapper.deleteRoleUser(id);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
