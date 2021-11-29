package com.czx.yimem.dao;

import com.czx.yimem.entity.Certificate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CertificateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Certificate record);

    int insertSelective(Certificate record);

    Certificate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Certificate record);

    int updateByPrimaryKey(Certificate record);

    List<Certificate> findBycertificate(Certificate record);
}
