package com.czx.yimem.service;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.CvFile;
import com.czx.yimem.entity.responsebean.BaseResp;

import java.util.List;

/**
 * 简历附件 (CvFile)表服务接口
 *
 * @author makejava
 * @since 2021-04-06 09:42:59
 */
public interface CvFileService {

    BaseResp vpncvFileServiceGet(Integer id);
    BaseResp vpncvFileServiceAdd(CvFile cvFile);
    BaseResp vpncvFileServiceUpdate(OssVO vo);
    BaseResp vpncvFileServiceDel(CvFile cvFile);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CvFile queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CvFile> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param cvFile 实例对象
     * @return 实例对象
     */
    CvFile insert(CvFile cvFile);

    /**
     * 修改数据
     *
     * @param cvFile 实例对象
     * @return 实例对象
     */
    CvFile update(CvFile cvFile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
