package com.czx.yimem.service.impl;

import com.czx.yimem.dao.*;
import com.czx.yimem.dto.JobStateVO;
import com.czx.yimem.entity.*;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.*;
import com.czx.yimem.utils.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@Service
public class JobServiceImpl implements JobService {
    @Resource
    private JobMapper jobMapper;
    @Autowired
    private JobCustomLightenEnumService jobCustomLightenEnumService;
    @Autowired
    private JobLightenEnumService jobLightenEnumService;
    @Autowired
    private JobLightspotService jobLightspotService;
    @Autowired
    private CompanyService companyService;
    @Resource
    private DeliverMapper deliverMapper;
    @Resource
    private JobCategoryMapper jobCategoryMapper;
    @Autowired
    private RedisAPI redisAPI;
    @Resource
    private CompanyMapper companyMapper;

    @Override
    public int deleteByPrimaryKey(Integer jobId) {
        return 0;
    }

    @Override
    public int insert(Job record) {
        return 0;
    }

    @Override
    public BaseResp insertSelective(OssVO vo) {
        try {
            Job job = JSON.toJavaObject(vo.getVpnjobAdd(), Job.class);

            if (job != null) {
                //是否兼职验证
                if (Xtool.isNull(job.getIsAllDay())) {
                    return new BaseResp(Constants.FAIL_CODE, "工作性质请选择全职或兼职");
                }
                if (Xtool.isNull(job.getName()) && Xtool.isNull(job.getJobCateId())
                        && Xtool.isNull(job.getCompanyId())) {
                    return new BaseResp(Constants.FAIL_CODE, "必填字段" + Constants.NO_NULL);
                }

                if (companyService.selectByPrimaryKey(job.getCompanyId()) == null) {
                    return new BaseResp(Constants.FAIL_CODE, "公司不存在");
                }
                //判断公司是否审核通过
                if (companyService.selectisAgree(job.getCompanyId()) <= 0) {
                    return new BaseResp(Constants.FAIL_CODE, "公司未审核通过，不能发布职位");
                }
//                完善企业收费和企业佣金
                JobCategory jobCategory = jobCategoryMapper.selectByjobCateId(job.getJobCateId());
                job.setMoney(jobCategory.getMoney());
                job.setBrokerage(jobCategory.getBrokerage());
                jobMapper.insertSelective(job);
                if (Xtool.isNull(job.getJobId())) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
                }
                List<JobCustomLightenEnum> customLightenEnums = job.getJobCustomLightspot();
                List<JobLightenEnum> jobLightenEnums = job.getJobLightspot();
                if (Xtool.isNotNull(jobLightenEnums)) {
                    List<JobLightspot> jobLightspots = new ArrayList<>();
                    jobLightenEnums.forEach(x -> {
                        JobLightspot jobLightspot = new JobLightspot();
                        jobLightspot.setJobId(job.getJobId());
                        jobLightspot.setJobLightenId(x.getJobLightenId());
                        jobLightspot.setState(Constants.SUCCEE_CODE + "");
                        jobLightspots.add(jobLightspot);
                    });
                    jobLightspotService.saveJobLightspots(jobLightspots);
                }
                if (Xtool.isNotNull(customLightenEnums)) {
                    customLightenEnums.forEach(x -> {
                        x.setJobId(job.getJobId());
                    });
                    jobCustomLightenEnumService.saveJobCustomLightenEnumbyjobId(customLightenEnums);
                }
                if (Xtool.isNotNull(job.getJobLightspotIds())) {
                    List<JobLightspot> jobLightspots = new ArrayList<>();
                    job.getJobLightspotIds().forEach(x -> {
                        JobLightspot jobLightspot = new JobLightspot();
                        jobLightspot.setJobId(job.getJobId());
                        jobLightspot.setJobLightenId(x);
                        jobLightspot.setState(Constants.SUCCEE_CODE + "");
                        jobLightspots.add(jobLightspot);
                    });
                    jobLightspotService.saveJobLightspots(jobLightspots);
                }
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
            }
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_NULL);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }


    @Override
    public Job selectByPrimaryKey(Integer jobId) {
        return null;
    }

    @Override
    public BaseResp updateByPrimaryKeySelective3(OssVO vo) {
        try {
            Job job = JSON.toJavaObject(vo.getVpnjobAdd(), Job.class);
            if (job != null) {
                if (Xtool.isNotNull(job.getState())) {
                    if ("2".equals(job.getState()+"")) {
                        job.setState("2");
                    }else if ("1".equals(job.getState()+"")){
                        job.setState("1");
                    }else {
                        job.setState("0");
                    }
                }else {
                    job.setState("0");
                }
                Integer count = jobMapper.updateByPrimaryKeySelective(job);
                if (count <= 0) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
                }
                List<JobCustomLightenEnum> customLightenEnums = job.getJobCustomLightspot();
                List<JobLightenEnum> jobLightenEnums = job.getJobLightspot();
                if (Xtool.isNotNull(jobLightenEnums)) {
                    List<JobLightspot> jobLightspots = new ArrayList<>();
                    jobLightenEnums.forEach(x -> {
                        JobLightspot jobLightspot = new JobLightspot();
                        jobLightspot.setJobLightenId(x.getJobLightenId());
                        jobLightspot.setJobId(job.getJobId());
                        jobLightspot.setState(Constants.SUCCEE_CODE + "");
                        jobLightspots.add(jobLightspot);
                    });
                    jobLightspotService.delByJob(job.getJobId());
                    jobLightspotService.saveJobLightspots(jobLightspots);
                }
                if (Xtool.isNotNull(customLightenEnums)) {
                    customLightenEnums.forEach(x -> {
                        x.setJobId(job.getJobId());
                    });
                    jobCustomLightenEnumService.delByjobId(job.getJobId());
                    jobCustomLightenEnumService.saveJobCustomLightenEnumbyjobId(customLightenEnums);
                }

                if (Xtool.isNotNull(job.getJobLightspotIds())) {
                    List<JobLightspot> jobLightspots = new ArrayList<>();
                    job.getJobLightspotIds().forEach(x -> {
                        JobLightspot jobLightspot = new JobLightspot();
                        jobLightspot.setJobId(job.getJobId());
                        jobLightspot.setJobLightenId(x);
                        jobLightspot.setState(Constants.SUCCEE_CODE + "");
                        jobLightspots.add(jobLightspot);
                    });
                    jobLightspotService.delByJob(job.getJobId());
                    jobLightspotService.saveJobLightspots(jobLightspots);
                }
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
            }
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_NULL);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }

    }


    @Override
    public BaseResp updateByPrimaryKeySelective2(OssVO vo) {
        try {
            Job job = JSON.toJavaObject(vo.getVpnjobUpdate(), Job.class);
            if (job != null) {
                if (Xtool.isNotNull(job.getState())) {
                    if (!"2".equals(job.getState()+"")) {
                        job.setState("0");
                    }
                }
                Integer count = jobMapper.updateByPrimaryKeySelective(job);
                if (count <= 0) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
                }
                List<JobCustomLightenEnum> customLightenEnums = job.getJobCustomLightspot();
                List<JobLightenEnum> jobLightenEnums = job.getJobLightspot();
                if (Xtool.isNotNull(jobLightenEnums)) {
                    List<JobLightspot> jobLightspots = new ArrayList<>();
                    jobLightenEnums.forEach(x -> {
                        JobLightspot jobLightspot = new JobLightspot();
                        jobLightspot.setJobLightenId(x.getJobLightenId());
                        jobLightspot.setJobId(job.getJobId());
                        jobLightspot.setState(Constants.SUCCEE_CODE + "");
                        jobLightspots.add(jobLightspot);
                    });
                    jobLightspotService.delByJob(job.getJobId());
                    jobLightspotService.saveJobLightspots(jobLightspots);
                }
                if (Xtool.isNotNull(customLightenEnums)) {
                    customLightenEnums.forEach(x -> {
                        x.setJobId(job.getJobId());
                    });
                    jobCustomLightenEnumService.delByjobId(job.getJobId());
                    jobCustomLightenEnumService.saveJobCustomLightenEnumbyjobId(customLightenEnums);
                }

                if (Xtool.isNotNull(job.getJobLightspotIds())) {
                    List<JobLightspot> jobLightspots = new ArrayList<>();
                    job.getJobLightspotIds().forEach(x -> {
                        JobLightspot jobLightspot = new JobLightspot();
                        jobLightspot.setJobId(job.getJobId());
                        jobLightspot.setJobLightenId(x);
                        jobLightspot.setState(Constants.SUCCEE_CODE + "");
                        jobLightspots.add(jobLightspot);
                    });
                    jobLightspotService.delByJob(job.getJobId());
                    jobLightspotService.saveJobLightspots(jobLightspots);
                }
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
            }
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_NULL);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }

    }

    @Override
    public BaseResp updateByPrimaryKeySelective4(OssVO vo) {
        try {
            Job job = JSON.toJavaObject(vo.getVpnjobUpdate(), Job.class);
            if (job != null) {
                if (Xtool.isNotNull(job.getState())) {
                    if ("2".equals(job.getState()+"")) {
                        job.setState("2");
                    }else if ("1".equals(job.getState()+"")){
                        job.setState("1");
                    }else {
                        job.setState("0");
                    }
                }else {
                    job.setState("0");
                }
                Integer count = jobMapper.updateByPrimaryKeySelective(job);
                if (count <= 0) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
                }
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
            }
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_NULL);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }

    }


    @Override
    public BaseResp updateByPrimaryKeySelective(OssVO vo) {
        try {
            Job job = JSON.toJavaObject(vo.getVpnjobUpdate(), Job.class);
            if (job != null) {
                if (Xtool.isNotNull(job.getState())) {
                    if ("2".equals(job.getState())) {
                        if (Xtool.isNotNull(job.getPriority())) {
                            return new BaseResp(Constants.FAIL_CODE, "存在停止招聘岗位，不可直接发布");
                        }
                    }
                }
                Integer count = jobMapper.updateByPrimaryKeySelective(job);
                if (count <= 0) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
                }
                List<JobCustomLightenEnum> customLightenEnums = job.getJobCustomLightspot();
                List<JobLightenEnum> jobLightenEnums = job.getJobLightspot();
                if (Xtool.isNotNull(jobLightenEnums)) {
                    List<JobLightspot> jobLightspots = new ArrayList<>();
                    jobLightenEnums.forEach(x -> {
                        JobLightspot jobLightspot = new JobLightspot();
                        jobLightspot.setJobLightenId(x.getJobLightenId());
                        jobLightspot.setJobId(job.getJobId());
                        jobLightspot.setState(Constants.SUCCEE_CODE + "");
                        jobLightspots.add(jobLightspot);
                    });
                    jobLightspotService.delByJob(job.getJobId());
                    jobLightspotService.saveJobLightspots(jobLightspots);
                }
                if (Xtool.isNotNull(customLightenEnums)) {
                    customLightenEnums.forEach(x -> {
                        x.setJobId(job.getJobId());
                    });
                    jobCustomLightenEnumService.delByjobId(job.getJobId());
                    jobCustomLightenEnumService.saveJobCustomLightenEnumbyjobId(customLightenEnums);
                }

                if (Xtool.isNotNull(job.getJobLightspotIds())) {
                    List<JobLightspot> jobLightspots = new ArrayList<>();
                    job.getJobLightspotIds().forEach(x -> {
                        JobLightspot jobLightspot = new JobLightspot();
                        jobLightspot.setJobId(job.getJobId());
                        jobLightspot.setJobLightenId(x);
                        jobLightspot.setState(Constants.SUCCEE_CODE + "");
                        jobLightspots.add(jobLightspot);
                    });
                    jobLightspotService.delByJob(job.getJobId());
                    jobLightspotService.saveJobLightspots(jobLightspots);
                }
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
            }
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_NULL);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }

    }

    @Override
    public BaseResp vpnjobUpdateStatesvc(OssVO vo) {
        try {
            JobStateVO jobStateVO = JSON.toJavaObject(vo.getVpnjobUpdate(), JobStateVO.class);
            if (jobStateVO != null) {
                if (Xtool.isNull(jobStateVO.getJobId()) || Xtool.isNull(jobStateVO.getState())) {
                    return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
                }
                if (jobMapper.findstate(jobStateVO.getJobId()) > 0 && Xtool.isNotNull(jobStateVO.getPriority())) {
                    return new BaseResp(Constants.FAIL_CODE, "存在停止招聘岗位，不可直接发布");
                }
                jobStateVO.getJobId().forEach(x -> {
                    jobMapper.updateByPrimaryKeySelective(new Job(x, jobStateVO.getState(), jobStateVO.getPriority()));
                });
                return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
            }
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_NULL);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }


    @Override
    public int updateByPrimaryKey(Job record) {
        return 0;
    }

    @Override
    public BaseResp selectByJob(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            Job job = JSON.toJavaObject(vo.getVpnjobGet(), Job.class);
            String userId = TokenUtil.getTokenUserId();
            List<Job> list = new ArrayList<>();
            if (job != null && Xtool.isNotNull(job.getCompanyId())&&Xtool.isNotNull(userId)&&userId.equals(companyMapper.finduserId(job.getCompanyId())+"")) {
                PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
                if (Xtool.isNull(job.getMapZoom())){
                    job.setMapZoom(Constants.ZMAP);
                }
                if (Xtool.isNotNull(job.getCompanyId())){
                    job.setMapZoom(Constants.QS_ISREAD);
                }
                list = jobMapper.selectByJob6(job);
            } else {
                if (Xtool.isNull(job.getMapZoom())){
                    job.setMapZoom(Constants.ZMAP);
                }
                if (Xtool.isNotNull(job.getCompanyId())){
                    job.setMapZoom(Constants.QS_ISREAD);
                }
                PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
                list = jobMapper.selectByJob3(job);
            }
            list.forEach(x -> {
                x.setDeliverMun(deliverMapper.selectcountByjobIdAndState(x.getJobId()));
                List<JobCustomLightenEnum> customLightenEnums = jobCustomLightenEnumService.findJobCustomLightenEnumbyjobId(x.getJobId());
                List<JobLightenEnum> jobLightenEnums = jobLightenEnumService.findJobLightenEnumbyjobId(x.getJobId());
                x.setJobCustomLightspot(customLightenEnums);
                x.setJobLightspot(jobLightenEnums);
            });
            Page<Job> jobPage = (Page<Job>) list;
            Map map = new HashMap();
            map.put("vpnjobGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp selectByJob2(OssVO vo) {
        try {
            List<Job> list = new ArrayList<>();
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Job job = JSON.toJavaObject(vo.getVpnjobGet(), Job.class);
            if (Xtool.isNotNull(job.getWeTO())) {
                if (Xtool.isNull(job.getMapZoom())){
                    job.setMapZoom(Constants.ZMAP);
                }
                if (job.getWeTO() == 2) {
                    list = jobMapper.selectByweTOJob(job);
                } else if (job.getWeTO() == 1) {
                    list = jobMapper.selectByzhJiJob(job);
                } else {
                    list = jobMapper.selectByJob(job);
                }
            } else {
                list = jobMapper.selectByJob(job);

            }
            list.forEach(x -> {
                x.setDeliverMun(deliverMapper.selectcountByjobIdAndState(x.getJobId()));
                List<JobCustomLightenEnum> customLightenEnums = jobCustomLightenEnumService.findJobCustomLightenEnumbyjobId(x.getJobId());
                List<JobLightenEnum> jobLightenEnums = jobLightenEnumService.findJobLightenEnumbyjobId(x.getJobId());
                x.setJobCustomLightspot(customLightenEnums);
                x.setJobLightspot(jobLightenEnums);
            });
            Page<Job> jobPage = (Page<Job>) list;
            Map map = new HashMap();
            map.put("vpnjobGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp selectByJobId(Integer id) {
        try {
            Job x = new Job();
            List<Job> list = jobMapper.selectByJob(new Job(id));
            jobMapper.updateReadcount(id);
            if (Xtool.isNotNull(list)) {
                x = list.get(0);
                x.setDeliverMun(deliverMapper.selectcountByjobIdAndState(id));
                List<JobCustomLightenEnum> customLightenEnums = jobCustomLightenEnumService.findJobCustomLightenEnumbyjobId(id);
                List<JobLightenEnum> jobLightenEnums = jobLightenEnumService.findJobLightenEnumbyjobId(id);
                Company company = companyMapper.selectByjobid(id);
                List<Integer> jobLightspotIds = jobMapper.selectJobLightspotIds(x.getJobId());
                x.setJobLightspotIds(jobLightspotIds);
                x.setJobCustomLightspot(customLightenEnums);
                x.setJobLightspot(jobLightenEnums);
                x.setCompany(company);
            }
            Map map = new HashMap();
            map.put("vpnjobGetResp", x);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp selectByJobId2(Integer id) {
        try {
            Job x = new Job();
            List<Job> list = jobMapper.selectByJob(new Job(id));

            if (Xtool.isNotNull(list)) {
                x = list.get(0);
                x.setDeliverMun(deliverMapper.selectcountByjobIdAndState(id));
                List<JobCustomLightenEnum> customLightenEnums = jobCustomLightenEnumService.findJobCustomLightenEnumbyjobId(id);
                List<JobLightenEnum> jobLightenEnums = jobLightenEnumService.findJobLightenEnumbyjobId(id);
                Company company = companyMapper.selectByjobid(id);
                if (!x.getShowcompany()){
                    x.setCompanyName("*****公司");
                    if (company!=null){
                        company.setName("*****公司");
                    }
                }
                List<Integer> jobLightspotIds = jobMapper.selectJobLightspotIds(x.getJobId());
                x.setJobLightspotIds(jobLightspotIds);
                x.setJobCustomLightspot(customLightenEnums);
                x.setJobLightspot(jobLightenEnums);
                x.setCompany(company);
            }
            //增加浏览量
            String userId = TokenUtil.getTokenUserId();
            log.info("userId:" + userId);
            if (Xtool.isNotNull(userId)) {
                Object vcode = redisAPI.get(CacheKeyManage.getVcode(userId + "job" + id));
                if (StringUtil.isNullObject(vcode)) {
                    //如果没有阅读+1
                    jobMapper.updateReadcount(id);
                    //设置24后过期
                    redisAPI.set(CacheKeyManage.getVcode(userId + "job" + id), Constants.SUCCEE_CODE + "", 24 * 60 * 60);
                }

            }
            Map map = new HashMap();
            map.put("vpnjobGetResp", x);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
    @Override
    public Job selectById(Integer jobId) {
        try {
            Job x = new Job();
            List<Job> list = jobMapper.selectByJob(new Job(jobId));
            if (Xtool.isNotNull(list)) {
                x = list.get(0);
                x.setDeliverMun(deliverMapper.selectcountByjobIdAndState(jobId));
                List<JobCustomLightenEnum> customLightenEnums = jobCustomLightenEnumService.findJobCustomLightenEnumbyjobId(jobId);
                List<JobLightenEnum> jobLightenEnums = jobLightenEnumService.findJobLightenEnumbyjobId(jobId);
                x.setJobCustomLightspot(customLightenEnums);
                x.setJobLightspot(jobLightenEnums);
            }
            return x;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String selectphone(Integer jobId) {
        return jobMapper.selectphone(jobId);
    }


    @Override
    public BaseResp delByjob(OssVO vo) {
        try {
            Job job = JSON.toJavaObject(vo.getVpnjobDel(), Job.class);
            Integer count = 0;
            if (Xtool.isNotNull(job.getJobId())) {
                count = jobMapper.deleteByPrimaryKey(job.getJobId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }


}
