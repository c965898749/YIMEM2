package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.UserDeliVO;
import com.czx.yimem.entity.User;
import com.czx.yimem.entity.responsebean.BaseResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    List<User> selectByUser(User user);

    Integer findphone(String phone);

    User findphone2(String phone);

    Integer findByuserCount(Integer userId);

    String findpasswordByuserId(Integer userId);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByopenid(String openid);

    BaseResp findByuser(OssVO vo);

    BaseResp vpncompanydeliverUserDetailGetsvc(Integer id);

    BaseResp addByuser(OssVO vo);

    BaseResp udateByuser(OssVO vo);

    BaseResp delByuser(OssVO vo);

    User findByuserId(Integer id);

    BaseResp vpnuserbasicDeli(Integer id);

    BaseResp inviteList(String inviteCode);
}
