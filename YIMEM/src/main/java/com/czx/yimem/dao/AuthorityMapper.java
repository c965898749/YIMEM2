package com.czx.yimem.dao;

import com.czx.yimem.dto.ButtonVO;
import com.czx.yimem.dto.MenuVO;
import com.czx.yimem.entity.Authority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface AuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    List<Authority> selectByRoleId(Integer roleId);

    List<Authority> selectByAuth(Set<Integer> authes);

    List<Authority> selectAllByAccount(String account);

    List<Authority> selectAllByAccount2(String account);

    List<Authority> selectAllByAccount4(String account);

    List<Authority> selectAllByAccount3();

    List<Integer> selectAllid(Integer id);

    Integer selectisAdmin(String account);

    List<Authority> getAll();

    List<Authority> getAll2();

    List<MenuVO> selectByFId(Integer id);

    List<String> selectByFFId(Integer id);

    List<String> selectButtonByFid(Integer id);

    List<String> selectButtonByFid2(ButtonVO buttonVO);

}
