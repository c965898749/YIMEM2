package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dto.*;
import com.czx.yimem.dao.AuthorityMapper;
import com.czx.yimem.entity.Authority;
import com.czx.yimem.entity.Department;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.AuthorityService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthorityServiceImpl implements AuthorityService {


@Resource
private AuthorityMapper authorityMapper;


    @Override
    public BaseResp getUserAuthority(String account) {
        List<Authority> authorities = authorityMapper.selectAllByAccount(account);
        //权限成树状结构
        List<TreeDataVO> rt = new ArrayList<>();
        //权限根节点
        for (Authority item : authorities) {
//            移除默认权限和管理员
            if (item.getId()==Constants.DEFAULT_ROLE||item.getId()==Constants.ADMIN_ROLE){
                continue;
            }
            if (item.getfId()==0) {
                TreeDataVO vo = new TreeDataVO();
                vo.setTitle(item.getAuthorityName());
                vo.setValue(item.getId());
                vo.setKey(item.getId());
                vo.setChildren(new ArrayList<>());
                vo.setChildren(findChildren(vo, authorities));
                rt.add(vo);
            }
        }
        return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,rt);
    }

    @Override
    public BaseResp getAll() {
        try {
            List<Authority> authorities=authorityMapper.getAll();
            //权限成树状结构
            List<TreeDataVO> rt = new ArrayList<>();
//        List<Authority> authorityList=new ArrayList<>();
            //权限根节点
            for (Authority item : authorities) {
    //            移除默认权限和管理员
                if (item.getId()==Constants.DEFAULT_ROLE||item.getId()==Constants.ADMIN_ROLE){
                    continue;
                }
                if (item.getfId()==0) {
                    TreeDataVO vo = new TreeDataVO();
                    vo.setTitle(item.getAuthorityName());
                    vo.setValue(item.getId());
                    vo.setKey(item.getId());
                    vo.setChildren(new ArrayList<>());
                    vo.setChildren(findChildren(vo, authorities));
                    rt.add(vo);
                }
            }
            return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,rt);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
    private List<TreeDataVO> findChildren(TreeDataVO p, List<Authority> list) {
        for (Authority item : list) {
            if (item.getfId() != 0 && item.getfId().equals(p.getKey())) {
                TreeDataVO vo = new TreeDataVO();
                vo.setChildren(new ArrayList<>());
                vo.setTitle(item.getAuthorityName());
                vo.setValue(item.getId());
                vo.setKey(item.getId());
                p.getChildren().add(vo);
            }
        }
        if (p.getChildren().size() == 0) {
            return p.getChildren();
        } else {
            for (TreeDataVO c : p.getChildren()) {
                c.setChildren(findChildren(c, list));

            }

            return p.getChildren();
        }

    }

    @Override
    public BaseResp getUserMenu(String account) {
        try {
            //权限成树状结构
            List<MenuVO> list=new ArrayList<>();
            if (Xtool.isNull(account)){
                return new BaseResp(Constants.FAIL_CODE,Constants.FAIL);
            }
//            所有权限
            List<Authority> all=authorityMapper.getAll2();
            //先判断是否是最高管理员
            if (authorityMapper.selectisAdmin(account)>0){
                //权限成树状结构
                //权限根节点
                for (Authority authority : all) {
                    //            移除默认权限和管理员
                    if (authority.getId()==Constants.DEFAULT_ROLE||authority.getId()==Constants.ADMIN_ROLE){
                        continue;
                    }
                    if (authority.getfId()==0) {
                        MenuVO vo = new MenuVO();
                        BeanUtils.copyProperties(authority,vo);
                        vo.setId(authority.getId());
                        vo.setRouter(authority.getAuthorityCode());
                        vo.setChildren(new ArrayList<>());
                        vo.setChildren(findChildren2(vo, all));
                        list.add(vo);
                    }
                }
                return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,list);
            }else {
//                所有自身权限
                Set set=new TreeSet();
//                List<Authority> authorityList = authorityMapper.selectAllByAccount2(account);
                List<Authority> authorityList = authorityMapper.selectAllByAccount4(account);
                for (Authority a:authorityList){
                    List<Integer> integerList=authorityMapper.selectAllid(a.getId());
                    set.addAll(integerList);
                }
//                菜单权限
                List<Authority> authorities = authorityMapper.selectByAuth(set);
                authorities.forEach(x->{
                    x.setButton(new ArrayList<>());
                    ButtonVO buttonVO=new ButtonVO();
                    buttonVO.setAccount(account);
                    buttonVO.setFId(x.getId());
                    List<String> stringList=authorityMapper.selectButtonByFid2(buttonVO);
                    if (Xtool.isNotNull(stringList)){
                        x.getButton().addAll(stringList);
                    }
                });
                //权限根节点
                for (Authority item : authorities) {
                    //            移除默认权限和管理员
                    if (item.getId()==Constants.DEFAULT_ROLE||item.getId()==Constants.ADMIN_ROLE){
                        continue;
                    }
                    if (item.getfId()==0) {
                        MenuVO menuVO=new MenuVO();
                        menuVO.setId(item.getId());
                        BeanUtils.copyProperties(item,menuVO);
                        menuVO.setRouter(item.getAuthorityCode());
                        menuVO.setChildren(new ArrayList<>());
                        menuVO.setChildren(findChildren2(menuVO, authorities));
                        list.add(menuVO);
                    }
                }
                return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,list);
            }
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);
        }
    }

    private List<MenuVO> findChildren2(MenuVO p, List<Authority> list) {
        for (Authority item : list) {
            if (item.getfId() != 0 && item.getfId().equals(p.getId())) {
                MenuVO vo = new MenuVO();
                vo.setChildren(new ArrayList<>());
                BeanUtils.copyProperties(item,vo);
                vo.setRouter(item.getAuthorityCode());
                vo.setId(item.getId());
                p.getChildren().add(vo);
            }
        }
        if (p.getChildren().size() == 0) {
            return p.getChildren();
        } else {
            for (MenuVO c : p.getChildren()) {
                c.setChildren(findChildren2(c, list));
            }

            return p.getChildren();
        }

    }

}
