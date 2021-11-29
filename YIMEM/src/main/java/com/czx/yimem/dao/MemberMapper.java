package com.czx.yimem.dao;

import com.czx.yimem.dto.ParmOV;
import com.czx.yimem.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    Integer findisPassword(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    String getName(String account);

    String selectphoneBydevId(Integer id);

    List<Member> search(ParmOV parmOV);

    Member findByMember(@Param("account") String account);

    Integer findaccount(@Param("account") String account);

    Integer findphone(@Param("phone") String phone);

    String findaccountByphone(@Param("phone") String phone);



}
