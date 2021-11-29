package com.czx.yimem.service.impl;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.CvFile;
import com.czx.yimem.dao.CvFileDao;
import com.czx.yimem.entity.DeliverDetail;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.CvFileService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.UploadUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 简历附件 (CvFile)表服务实现类
 *
 * @author makejava
 * @since 2021-04-06 09:42:59
 */
@Service("cvFileService")
public class CvFileServiceImpl implements CvFileService {
    @Resource
    private CvFileDao cvFileDao;

    @Override
    public BaseResp vpncvFileServiceGet(Integer userid) {
        try {
            //暂不用分页
//            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
//            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            CvFile cvFile=new CvFile();
            cvFile.setUserId(userid);
            List<CvFile> list = cvFileDao.queryAll(cvFile);
//            Page<DeliverDetail> jobPage = (Page<DeliverDetail>) list;
            Map map = new HashMap();
            map.put("cvFile", list);
//            map.put("totalElements", jobPage.getTotal());
//            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpncvFileServiceAdd(CvFile cvFile) {
        try {
            Map map = new HashMap();
            List list=new ArrayList();
            CvFile file=cvFileDao.queryByuserId(cvFile.getUserId());
            if (file!=null){
                file.setUrl(cvFile.getUrl());
                file.setFilename(cvFile.getFilename());
                file.setFilesize(cvFile.getFilesize());
                UploadUtil.deleteObject("ax-jhrc", file.getUrl());
                cvFileDao.update(file);
                list.add(file);
                map.put("cvFile", list);
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
            }else {
                cvFileDao.insert(cvFile);
                cvFile.setCreatetime(new Date());
                list.add(cvFile);
                map.put("cvFile", list);
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpncvFileServiceUpdate(OssVO vo) {
        return null;
    }

    @Override
    @Transactional
    public BaseResp vpncvFileServiceDel(CvFile cvFile) {
        CvFile file = cvFileDao.queryById(cvFile.getId());
        if (file==null){
            return new BaseResp(Constants.FAIL_CODE, "文件不存在");
        }
        Integer count = cvFileDao.deleteBycvFileId(cvFile);
        if (count>0){
            UploadUtil.deleteObject("ax-jhrc", file.getUrl());
        }
        return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CvFile queryById(Integer id) {
        return this.cvFileDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<CvFile> queryAllByLimit(int offset, int limit) {
        return this.cvFileDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param cvFile 实例对象
     * @return 实例对象
     */
    @Override
    public CvFile insert(CvFile cvFile) {
        this.cvFileDao.insert(cvFile);
        return cvFile;
    }

    /**
     * 修改数据
     *
     * @param cvFile 实例对象
     * @return 实例对象
     */
    @Override
    public CvFile update(CvFile cvFile) {
        this.cvFileDao.update(cvFile);
        return this.queryById(cvFile.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.cvFileDao.deleteById(id) > 0;
    }
}
