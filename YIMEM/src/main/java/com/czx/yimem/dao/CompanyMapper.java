package com.czx.yimem.dao;

import com.czx.yimem.entity.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer companyId);

    int insert(Company record);

    int selectisAgree(Integer companyId);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer companyId);

    Company selectByjobid(Integer jobId);

    List<Company> selectByPrimaryKey2(String phone);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    Integer finduserId(Integer companyId);

    Integer companydeliver(Integer userId,Integer id);

    List<Company> findBycompany(Company record);

//    List<Company> findBycompany2(Company record);
    Integer findByUtoU(Integer userId, Integer comuserId);
}
