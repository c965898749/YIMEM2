package com.czx.yimem.dao;

import com.czx.yimem.dto.JobCategoryOV;
import com.czx.yimem.dto.JobCategoryVO;
import com.czx.yimem.entity.JobCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobCategory record);

    int insertSelective(JobCategory record);

    JobCategory selectByPrimaryKey(Integer id);

    JobCategory selectByjobCateId(@Param("jobCateId") Integer jobCateId);

    int updateByPrimaryKeySelective(JobCategory record);

    int updateByPrimaryKey(JobCategory record);

    List<JobCategory> findByjobCategory(JobCategory record);

    List<JobCategory> findByjobCategory10(JobCategory record);

    List<JobCategoryVO> findByjobCategory2(JobCategory record);

    List<JobCategoryOV> findByjobCategory3(JobCategory record);

    List<JobCategoryOV> findByjobCategory4(JobCategory record);

    List<JobCategoryVO> findByjobCategory5(JobCategory record);

    Integer findByjobCategory7(Integer id);

    List<Integer> findByjobCategory9(Integer id);

    List<JobCategoryOV> findByjobCategory8(List<Integer> ids);

    JobCategoryOV findByjobCategory6(short id);

    int updateByJobCateId(JobCategory record);

    int deleteByJobCateId(JobCategory record);


}
