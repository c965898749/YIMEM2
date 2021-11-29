package com.czx.yimem.dao;

import com.czx.yimem.entity.CvFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 简历附件 (CvFile)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-06 09:42:59
 */
@Mapper
public interface CvFileDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CvFile queryById(Integer id);

    CvFile queryByuserId(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CvFile> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);



    /**
     * 通过实体作为筛选条件查询
     *
     * @param cvFile 实例对象
     * @return 对象列表
     */
    List<CvFile> queryAll(CvFile cvFile);

    /**
     * 新增数据
     *
     * @param cvFile 实例对象
     * @return 影响行数
     */
    int insert(CvFile cvFile);

    /**
     * 修改数据
     *
     * @param cvFile 实例对象
     * @return 影响行数
     */
    int update(CvFile cvFile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int deleteBycvFileId(CvFile cvFile);

}
