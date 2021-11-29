package com.czx.yimem.dao;

import com.czx.yimem.dto.UserDeliVO;
import com.czx.yimem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("userId") Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByopenid(@Param("openid")String openid);

    List<User> selectByUser(User user);

    User findByuserId(Integer id);

    UserDeliVO vpnuserbasicDeli(Integer id);

    Integer findphone(String phone);

    Integer findInviteCode(String inviteCode);

    User findphone2(String phone);

    Integer findByuserCount(Integer userId);

    String findpasswordByuserId(Integer userId);

    List<User> inviteList(String inviteCode);

    User selectByInviteCode(@Param("inviteCode") String inviteCode);
}
