package com.czx.yimem.dao;

import com.czx.yimem.entity.Protocol;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProtocolMapper {
    int deleteByPrimaryKey(Integer prId);

    int insert(Protocol record);

    int insertSelective(Protocol record);

    Protocol selectByPrimaryKey(Integer prId);

    Protocol selectBytouser(String touser);

    int updateByPrimaryKeySelective(Protocol record);

    int updateState(Protocol record);

    int updateByPrimaryKey(Protocol record);

    List<Integer> findTimeUpid();

    List<Protocol> findByprotocol(Protocol record);

    void updatefindTimeUp(Integer id);
}
