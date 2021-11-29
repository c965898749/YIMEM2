package com.czx.yimem.service.impl;

import com.czx.yimem.dao.JobCategoryMapper;
import com.czx.yimem.dao.QsCategoryJobsMapper;
import com.czx.yimem.dto.*;
import com.czx.yimem.entity.Authority;
import com.czx.yimem.entity.JobCategory;
import com.czx.yimem.entity.QsCategory;
import com.czx.yimem.entity.QsCategoryJobs;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.JobCategoryService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.CreateRandomUtil;
import com.czx.yimem.utils.StringUtils;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class JobCategoryServiceImpl implements JobCategoryService {
    @Resource
    private JobCategoryMapper jobCategoryMapper;
    @Resource
    private QsCategoryJobsMapper qsCategoryJobsMapper;

    @Override
    public int deleteByPrimaryKey(Integer jobCateId) {
        return 0;
    }

    @Override
    public int insert(JobCategory record) {
        return 0;
    }

    @Override
    public int insertSelective(JobCategory record) {
        return 0;
    }

    @Override
    public JobCategory selectByPrimaryKey(Integer jobCateId) {
        return jobCategoryMapper.selectByjobCateId(jobCateId);
    }

    @Override
    public int updateByPrimaryKeySelective(JobCategory record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(JobCategory record) {
        return 0;
    }

    @Override
    public BaseResp selectByjobcategory(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            JobCategory jobCategory = JSON.toJavaObject(vo.getVpnjobcategoryGet(), JobCategory.class);
            List<JobCategory> list = jobCategoryMapper.findByjobCategory(jobCategory);
            Page<JobCategory> jobPage = (Page<JobCategory>) list;
            Map map = new HashMap();
            map.put("vpnjobcategoryGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp selectByjobcategory3(OssVO vo) {
        try {
            JobCategory jobCategory = JSON.toJavaObject(vo.getVpnjobcategoryGet(), JobCategory.class);
            List<JobCategory> list = jobCategoryMapper.findByjobCategory10(jobCategory);
            Map map = new HashMap();
            map.put("vpnjobcategoryGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
    @Override
    public BaseResp selectByjobcategory2(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            JobCategory jobCategory = JSON.toJavaObject(vo.getVpnjobcategoryGet(), JobCategory.class);
            //当名字为空时查出所有类似不递归
            if (Xtool.isNotNull(jobCategory.getName())) {
                PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
                List<JobCategoryVO> list = jobCategoryMapper.findByjobCategory2(jobCategory);
                Page<JobCategoryVO> jobPage = (Page<JobCategoryVO>) list;
                Map map = new HashMap();
                map.put("vpnjobcategoryGetResp", list);
                map.put("totalElements", jobPage.getTotal());
                map.put("totalPages", jobPage.getPages());
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
            } else if (Xtool.isNull(jobCategory.getEndTime())&&Xtool.isNull(jobCategory.getStartTime())){
                //查出所有根
                PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
                List<JobCategoryOV> gen = jobCategoryMapper.findByjobCategory4(new JobCategory());
                List<JobCategoryOV> all = jobCategoryMapper.findByjobCategory3(new JobCategory());
                List list = new ArrayList();
                for (JobCategoryOV category : gen) {
                    if (category.getParentId() == 0) {
                        category.setChildren(new ArrayList());
                        category.setChildren(findChildren(category, all));
                        if (Xtool.isNull(category.getChildren())) {
                            JobCategoryVO ov = new JobCategoryVO();
                            BeanUtils.copyProperties(category, ov);
                            list.add(ov);
                        } else {
                            list.add(category);
                        }
                    }
                }
                Page<JobCategoryOV> jobPage = (Page<JobCategoryOV>) gen;
                Map map = new HashMap();
                map.put("vpnjobcategoryGetResp", list);
                map.put("totalElements", jobPage.getTotal());
                map.put("totalPages", jobPage.getPages());
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
            } else {
////                //按时间查出最最子数据
                List list = new ArrayList();
                List<JobCategoryOV> gen=new ArrayList<>();
                List<JobCategoryVO> zi = jobCategoryMapper.findByjobCategory5(jobCategory);
                Set set=new TreeSet();
                Set set2=new TreeSet();
                if (Xtool.isNotNull(zi)){
                    for (JobCategoryVO categoryVO:zi){
                        Integer k=jobCategoryMapper.findByjobCategory7(categoryVO.getJobCateId());
                        List<Integer> integerList=jobCategoryMapper.findByjobCategory9(categoryVO.getJobCateId());
                        set.add(k);
                        set2.addAll(integerList);
                    }
                    if (!set.isEmpty()){
                        List<Integer> result = new ArrayList<>(set);
                        PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
                        gen =jobCategoryMapper.findByjobCategory8(result);
//                        所有满足条件的节点
                        List<JobCategoryOV> all = jobCategoryMapper.findByjobCategory8(new ArrayList<Integer>(set2));
                        for (JobCategoryOV category : gen) {
                            if (category.getParentId() == 0) {
                                category.setChildren(new ArrayList());
                                category.setChildren(findChildren(category, all));
                                if (Xtool.isNull(category.getChildren())) {
                                    JobCategoryVO ov = new JobCategoryVO();
                                    BeanUtils.copyProperties(category, ov);
                                    list.add(ov);
                                } else {
                                    list.add(category);
                                }
                            }
                        }

                    }
                    Page<JobCategoryOV> jobPage = (Page<JobCategoryOV>) gen;
                    Map map = new HashMap();
                    map.put("vpnjobcategoryGetResp", list);
                    map.put("totalElements", jobPage.getTotal());
                    map.put("totalPages", jobPage.getPages());
                    return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
                }else {
                    Map map = new HashMap();
                    map.put("vpnjobcategoryGetResp", list);
                    map.put("totalElements", 0);
                    map.put("totalPages", 0);
                    return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

//    public JobCategoryOV getParentCategoryObject(JobCategoryOV category, List<JobCategoryOV> all) {
//        if (category.getParentId() == 0) {
//            return category;
//        }
//        JobCategoryOV tblCategory=new JobCategoryOV();
//        for (JobCategoryOV jobCategoryOV: all) {
//            if (jobCategoryOV.getJobCateId()==category.getParentId()){
//                jobCategoryOV.setChildren(new ArrayList());
//                jobCategoryOV.setKey(Integer.valueOf((StringUtils.Makemsgcode())));
//                jobCategoryOV.setId(Integer.valueOf((StringUtils.Makemsgcode())));
//                jobCategoryOV.getChildren().add(category);
//                BeanUtils.copyProperties(jobCategoryOV,tblCategory);
//            }
//        }
////        JobCategoryOV tblCategory = all.stream().filter(
////                x -> Objects.equals(x.getJobCateId(), category.getParentId())
////        ).findFirst().get();
//
//        return getParentCategoryObject(tblCategory, all);
//    }

    private List findChildren(JobCategoryOV p, List<JobCategoryOV> list) {
        for (JobCategoryOV item : list) {
            if (item.getParentId() != 0 && item.getParentId() == p.getJobCateId()) {
                item.setChildren(new ArrayList<>());
                p.getChildren().add(item);
            }
        }
        if (Xtool.isNull(p.getChildren())) {
            return p.getChildren();
        } else {
            Iterator it = p.getChildren().iterator();
            List bb = new ArrayList();
            while (it.hasNext()) {
                JobCategoryOV kk = (JobCategoryOV) it.next();
                kk.setChildren(findChildren(kk, list));
                if (Xtool.isNull(kk.getChildren())) {
                    JobCategoryVO ov = new JobCategoryVO();
                    BeanUtils.copyProperties(kk, ov);
                    it.remove();
                    bb.add(ov);
//                    p.getChildren().add(ov);
                }
            }
            p.getChildren().addAll(bb);

//            for (Object c : p.getChildren()) {
//                JobCategoryOV kk=(JobCategoryOV)c;
//                kk.setChildren(findChildren(kk, list));
//                if (Xtool.isNull(kk.getChildren())){
//                    JobCategoryVO ov=new JobCategoryVO();
//                    BeanUtils.copyProperties(kk,ov);
//                    p.getChildren().remove(c);
//                    p.getChildren().add(ov);
//                }
//
//            }

            return p.getChildren();
        }

    }

    @Override
    public BaseResp addByjobcategory(OssVO vo) {
        try {
            JobCategory jobCategory = JSON.toJavaObject(vo.getVpnjobcategoryAdd(), JobCategory.class);
            if (Xtool.isNull(jobCategory.getParentId())) {
                return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
            }
            //职位列表插入职位类型
            QsCategoryJobs qsCategoryJobs = new QsCategoryJobs();
            qsCategoryJobs.setParentid(jobCategory.getParentId());
            qsCategoryJobs.setCategoryname(jobCategory.getName());
            qsCategoryJobsMapper.insertSelective(qsCategoryJobs);
            jobCategory.setJobCateId(qsCategoryJobs.getId());
            jobCategoryMapper.insertSelective(jobCategory);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateByjobcategory(OssVO vo) {
        try {
            JobCategory jobCategory = JSON.toJavaObject(vo.getVpnjobcategoryUpdate(), JobCategory.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobCategory.getJobCateId())) {
                count = jobCategoryMapper.updateByJobCateId(jobCategory);
                //更改职业
                if (Xtool.isNotNull(jobCategory.getName())) {
                    QsCategoryJobs qsCategoryJobs = new QsCategoryJobs();
                    qsCategoryJobs.setParentid(jobCategory.getParentId());
                    qsCategoryJobs.setId(jobCategory.getJobCateId());
                    qsCategoryJobs.setCategoryname(jobCategory.getName());
                    qsCategoryJobsMapper.updateByPrimaryKeySelective(qsCategoryJobs);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delByjobcategory(OssVO vo) {
        try {
            JobCategory jobCategory = JSON.toJavaObject(vo.getVpnjobcategoryDel(), JobCategory.class);
            Integer count = 0;
            if (Xtool.isNotNull(jobCategory.getJobCateId())) {
                count = jobCategoryMapper.deleteByJobCateId(jobCategory);
                qsCategoryJobsMapper.deleteByPrimaryKey(jobCategory.getJobCateId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
