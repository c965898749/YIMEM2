package com.czx.yimem.controller;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.ParmVO;
import com.czx.yimem.entity.Job;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.*;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Resp;
import com.czx.yimem.utils.UserLoginToken;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 职业
 *
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@RequestMapping("vpnservice")
@RestController
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private JobLightenEnumService jobLightenEnumService;
    @Autowired
    private JobCustomLightenEnumService jobCustomLightenEnumService;
    @Autowired
    private JobCategoryService jobCategoryService;
    @Autowired
    private Resp resp;
    @Autowired
    private QsCategoryJobsService qsCategoryJobsService;
    /**
     * 4.1.	岗位查询接口
     *
     * @param vo
     * @return
     */
    @RequestMapping("/vpnjobGetsvc")
    public BaseResp vpnjobGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobService.selectByJob(vo);
        return baseResp;
    }

    /**
     * 4.1.	岗位详细查询接口
     *
     * @param
     * @return
     */
    @RequestMapping("/vpnjobGetsvc/{id}")
    public BaseResp vpnjobGetsvc(@PathVariable int id) {
        BaseResp baseResp = jobService.selectByJobId(id);
        return baseResp;
    }

    /**
     * 4.1.	岗位详细查询接口
     *
     * @param
     * @return
     */
    @RequestMapping("/vpnjobGetbyidsvc")
    public BaseResp vpnjobGetsvcid(@RequestBody ParmVO parmVO) {
        if (Xtool.isNotNull(parmVO.getId())) {
            return jobService.selectByJobId2(parmVO.getId());
        } else {
            return new BaseResp(Constants.FAIL_CODE, Constants.FAIL, "参数" + Constants.NO_STIPULATE);
        }
    }

    /**
     * 4.2.	岗位新增接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobAddsvc")
    public BaseResp vpnjobAddsvc(@RequestBody OssVO vo) {
        Job job = JSON.toJavaObject(vo.getVpnjobAdd(), Job.class);
        if (job == null || Xtool.isNull(job.getCompanyId())) {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
        }
        return Xtool.isNotNull(job.getJobId())?resp.comParmResp(job.getCompanyId(), jobService.updateByPrimaryKeySelective3(vo)):resp.comParmResp(job.getCompanyId(), jobService.insertSelective(vo));
    }

    /**
     * 4.3.	岗位修改接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobUpdatesvc")
    public BaseResp vpnjobUpdatesvc(@RequestBody OssVO vo) {
        Job job = JSON.toJavaObject(vo.getVpnjobUpdate(), Job.class);
        if (job == null || Xtool.isNull(job.getCompanyId())) {
            return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
        }
        return resp.comParmResp(job.getCompanyId(), jobService.updateByPrimaryKeySelective4(vo));
    }

    /**
     * 4.4.	岗位删除接口
     *
     * @param vo
     * @return
     */
//    @UserLoginToken
//    @RequestMapping("/vpnjobDelsvc")
//    public BaseResp vpnjobDelsvc(@RequestBody OssVO vo) {
//        Job job = JSON.toJavaObject(vo.getVpnjobDel(), Job.class);
//        if (job == null || Xtool.isNull(job.getCompanyId())) {
//            return new BaseResp(Constants.FAIL_CODE, Constants.NO_STIPULATE);
//        }
//        return resp.comParmResp(job.getCompanyId(), jobService.vpnjobUpdateStatesvc(vo));
//    }

    /**
     * 4.26.	通用职位亮点查询接口
     *
     * @param
     * @return
     */
    @RequestMapping("/vpnjobLightenEnumGetsvc")
    public BaseResp vpnjobLightenEnumGetsvc() {
        BaseResp baseResp = jobLightenEnumService.selectByJobLighten2();
        return baseResp;
    }

    /**
     * 4.27.	自定义职位亮点查询接口
     *
     * @param vo
     * @return
     */
    @UserLoginToken
    @RequestMapping("/vpnjobCustomLightenEnumGetsvc")
    public BaseResp vpnjobCustomLightenEnumGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobCustomLightenEnumService.selectByJobCustomLighten(vo);
        return baseResp;
    }

    /**
     * 4.16.	职位类别企佣收费查询接口
     *
     * @param
     * @return
     */
    @RequestMapping("/vpnjobcategory2Getsvc")
    public BaseResp vpnjobcategory2Getsvc() {
        BaseResp baseResp=qsCategoryJobsService.findByqsCategoryJobs3();
        return baseResp;
    }

    @RequestMapping("/vpnjobcategoryGetsvc")
    public BaseResp vpnjobcategoryGetsvc(@RequestBody OssVO vo) {
        BaseResp baseResp = jobCategoryService.selectByjobcategory3(vo);
        return baseResp;
    }

//    /**
//     *4.60.	职位类别企佣收费新增接口
//     * @param vo
//     * @return
//     */
//    @RequestMapping("/vpnjobcategoryAddsvc")
//    public BaseResp vpnjobcategoryAddsvc(@RequestBody OssVO vo){
//        BaseResp baseResp=jobCategoryService.addByjobcategory(vo);
//        return baseResp;
//    }
//
//    /**
//     *4.104.	职位类别企佣收费修改
//     * @param vo
//     * @return
//     */
//    @RequestMapping("/vpnjobcategoryUpdatesvc")
//    public BaseResp updateByjobcategory(@RequestBody OssVO vo){
//        BaseResp baseResp=jobCategoryService.updateByjobcategory(vo);
//        return baseResp;
//    }
//
//    /**
//     * 4.148.	职位类别企佣收费删除接口
//     * @param vo
//     * @return
//     */
//    @RequestMapping("/vpnjobcategoryDelsvc")
//    public BaseResp vpnjobcategoryDelsvc(@RequestBody OssVO vo){
//        BaseResp baseResp=jobCategoryService.delByjobcategory(vo);
//        return baseResp;
//    }
}
