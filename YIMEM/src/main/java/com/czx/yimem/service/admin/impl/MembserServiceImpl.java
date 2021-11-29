package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.*;
import com.czx.yimem.dto.*;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.DepartmentService;
import com.czx.yimem.service.admin.MembserService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.PhoneUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class MembserServiceImpl implements MembserService {


    @Resource
    private MemberMapper memberMapper;

    @Resource
    private GroupMemberMapper groupMemberMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private AuthorityMapper authorityMapper;

    @Resource
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public BaseResp create(MemberVO member) {
        try {
            Member item = new Member();
            BeanUtils.copyProperties(member, item);
            //验证机制
            //验证手机是否合规

            if (Xtool.isNull(item.getName())) {
                return new BaseResp(Constants.FAIL_CODE, "成员姓名不能为空");
            }
            if (Xtool.isNull(item.getPhone())) {
                return new BaseResp(Constants.FAIL_CODE, "成员电话不能为空");
            }
            if (!PhoneUtil.regular(member.getPhone())){
                return new BaseResp(Constants.FAIL_CODE,"手机号"+Constants.NO_STIPULATE);
            }
            if (Xtool.isNull(item.getAccount())) {
                return new BaseResp(Constants.FAIL_CODE, "成员账号不能为空");
            }

            if (StringUtils.isEmpty(item.getPassword())) {
                item.setPassword("1234");
            }
            if (memberMapper.findaccount(item.getAccount()) > 0) {
                return new BaseResp(Constants.FAIL_CODE, "账号已存在");
            }
            if (memberMapper.findphone(item.getPhone()) > 0) {
                return new BaseResp(Constants.FAIL_CODE, "电话已存在");
            }
            item.setState("1");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            item.setPassword(passwordEncoder.encode(item.getPassword()));
            memberMapper.insert(item);
            member.setId(item.getId());
            //部门关系
//            if (member.getDeps() != null) {
//                for (DepartmentVO d : member.getDeps()) {
//                    GroupMember record = new GroupMember();
//                    record.setDepId(d.getDepId());
//                    record.setmUserId(item.getId());
//                    record.setState("1");
//                    groupMemberMapper.insert(record);
//                }
//            }
            if (member.getDeps() != null) {
                GroupMember record = new GroupMember();
                record.setDepId(member.getDeps().getDepId());
                record.setmUserId(item.getId());
                record.setState("1");
                groupMemberMapper.insert(record);
            }
            //角色关系
//            if (member.getRoles() != null) {
//                for (RoleVO r : member.getRoles()) {
//                    UserRole record = new UserRole();
//                    record.setState("1");
//                    record.setMemberId(item.getId());
//                    record.setRoleId(r.getId());
//                    userRoleMapper.insert(record);
//                }
//            }
            if (member.getRoles() != null) {
                UserRole record = new UserRole();
                record.setState("1");
                record.setMemberId(item.getId());
                record.setRoleId(member.getRoles().getId());
                userRoleMapper.insert(record);
            }
            //添加默认员工角色（用于登录）
            userRoleMapper.insert(new UserRole(item.getId(), Constants.DEFAULT_ROLE, item.getCreateTime(), "1"));
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        } catch (BeansException e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp search(MemberDTO dto) {
        try {
            ParmOV parmOV = new ParmOV();
            parmOV.setKey(dto.getKeyword());
            if (Xtool.isNotNull(dto.getDepId())) {
                List<String> list = Arrays.asList(departmentService.getAllBYid(Integer.parseInt(dto.getDepId())).split(","));
                parmOV.setIds(list);
            }
            PageVO pageVO = JSON.toJavaObject(dto.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            List<Member> ms = memberMapper.search(parmOV);
            List<MemberVO> vos = ms.stream().map(m -> {
                MemberVO n = new MemberVO();
                BeanUtils.copyProperties(m, n);
                n.setPassword("");
                //TODO 优化
                List<Role> roles = roleMapper.selectByMemberId(n.getId());
                n.setRoles(new Role());
                if (Xtool.isNotNull(roles)) {
                    n.setRoles(roles.get(0));
                }
//                n.setRoles(roles.stream().map(r -> {
//                    RoleVO rvo = new RoleVO();
//                    BeanUtils.copyProperties(r, rvo);
//                    return rvo;
//                }).collect(Collectors.toList()));
                List<Department> groupMembers = departmentMapper.selectByMember(n.getId());
                n.setDeps(new Department());
                if (Xtool.isNotNull(groupMembers)) {
                    n.setDeps(groupMembers.get(0));
                }
//                n.setDeps(groupMembers.stream().map(r->{
//                    DepartmentVO departmentVO=new DepartmentVO();
//                    BeanUtils.copyProperties(r,departmentVO);
//                    return departmentVO;
//                }).collect(Collectors.toList()));
                return n;
            }).collect(Collectors.toList());
            Page<Member> jobPage = (Page<Member>) ms;
            Map map = new HashMap();
            map.put("content", vos);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            //加入角色信息
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }


    @Override
    public BaseResp update(MemberVO member) {
        try {
            if (Xtool.isNull(member.getId())){
                return new BaseResp(Constants.FAIL_CODE,"员工"+Constants.NO_NULL);
            }
            Member m=new Member();
            BeanUtils.copyProperties(member, m);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (Xtool.isNotNull(member.getPassword())) {
                m.setPassword(passwordEncoder.encode(member.getPassword()));
            }else if(memberMapper.findisPassword(member.getId())>0) {
                m.setPassword(passwordEncoder.encode("1234"));

            }
            memberMapper.updateByPrimaryKeySelective(m);
            if (member.getDeps() != null) {
                //部门关系,先删除再插入
                groupMemberMapper.deleteByMember(member.getId());
                GroupMember record = new GroupMember();
                record.setDepId(member.getDeps().getDepId());
                record.setmUserId(member.getId());
                record.setState("1");
                groupMemberMapper.insert(record);
            }
            if (member.getRoles() != null) {
                //角色关系,先删除后插入
                userRoleMapper.deleteByMember(member.getId());
                UserRole record = new UserRole();
                record.setState("1");
                record.setMemberId(member.getId());
                record.setRoleId(member.getRoles().getId());
                userRoleMapper.insert(record);
            }
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        } catch (BeansException e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public void enable(MemberVO member) {

        Member record = new Member();
        record.setId(member.getId());
        record.setState(member.getState());
        memberMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void disenable(MemberVO member) {
        Member record = new Member();
        record.setId(member.getId());
        record.setState(member.getState());
        memberMapper.updateByPrimaryKeySelective(record);

    }

    @Override
    public Member findByMember(String account) {
        Member member = memberMapper.findByMember(account);
        List<Authority> authorities = authorityMapper.selectAllByAccount(account);
        if (Xtool.isNotNull(authorities)) {
            member.setAuthorityList(authorities);
        }
        return member;
    }

    @Override
    public BaseResp delete(MemberVO member) {
        if (Xtool.isNotNull(member.getId())) {
            memberMapper.deleteByPrimaryKey(member.getId());
            groupMemberMapper.deleteByMember(member.getId());
            userRoleMapper.deleteByMember(member.getId());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        }
        return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
    }

    @Override
    public String getName(String account) {
        return memberMapper.getName(account);
    }

}
