package com.czx.yimem.dao;

import com.czx.yimem.entity.Complaint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplaintMapper {
    int deleteByPrimaryKey(Integer comId);

    int insert(Complaint record);

    int insertSelective(Complaint record);

    Complaint selectByPrimaryKey(Integer comId);

    int updateByPrimaryKeySelective(Complaint record);

    int updateByPrimaryKey(Complaint record);

    List<Complaint> findBycomplain(Complaint record);
}
