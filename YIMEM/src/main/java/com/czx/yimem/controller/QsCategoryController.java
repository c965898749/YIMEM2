package com.czx.yimem.controller;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 公共接口
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@RequestMapping("vpnservice")
@RestController
public class QsCategoryController {

    @Autowired
    private QsCategoryService qsCategoryService;
    @Autowired
    private QsCategoryGroupService qsCategoryGroupService;
    @Autowired
    private QsCategoryJobsService qsCategoryJobsService;
    @Autowired
    private QsCategoryMajorService qsCategoryMajorService;
    @Autowired
    private RmProvinceService rmProvinceService;
    @Autowired
    private RmCountyService rmCountyService;
    @Autowired
    private RmCityService rmCityService;
    /**
     * 4.181.	通用枚举查询接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnqsCategoryGetsvc")
    public BaseResp vpnqsCategoryGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=qsCategoryService.findByqsCategory(vo);
        return baseResp;
    }

    /**
     * 4.182.	通用枚举修改接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnqsCategoryUpdatesvc")
    public BaseResp vpnqsCategoryUpdatesvc(@RequestBody OssVO vo){
        BaseResp baseResp=qsCategoryService.updateByqsCategory(vo);
        return baseResp;
    }

    /**
     *4.183.	通用枚举新增接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnqsCategoryAddsvc")
    public BaseResp vpnqsCategoryAddsvc(@RequestBody OssVO vo){
        BaseResp baseResp=qsCategoryService.addByqsCategory(vo);
        return baseResp;
    }

    /**
     *4.184.	通用枚举删除接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnqsCategoryDelsvc")
    public BaseResp vpnqsCategoryDelsvc(@RequestBody OssVO vo){
        BaseResp baseResp=qsCategoryService.delByqsCategory(vo);
        return baseResp;
    }

    /**
     * 4.185.	分类查询接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnqsCategoryGroupGetsvc")
    public BaseResp vpnqsCategoryGroupGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=qsCategoryGroupService.findByqsCategoryGroup(vo);
        return baseResp;
    }


    /**
     * 4.186.	职业类型查询接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnqsCategoryJobsGetsvc")
    public BaseResp vpnqsCategoryJobsGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=qsCategoryJobsService.findByqsCategoryJobs(vo);
        return baseResp;
    }


    /**
     * 4.187.	职业类型新增接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnqsCategoryJobsAddsvc")
    public BaseResp vpnqsCategoryJobsAddsvc(@RequestBody OssVO vo){
        BaseResp baseResp=qsCategoryJobsService.addByqsCategoryJobs(vo);
        return baseResp;
    }

    /**
     * 4.188.	职业类型删除接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnqsCategoryJobsDelsvc")
    public BaseResp vpnqsCategoryJobsDelsvc(@RequestBody OssVO vo){
        BaseResp baseResp=qsCategoryJobsService.delByqsCategoryJobs(vo);
        return baseResp;
    }

    /**
     * 4.189.	职业类型更新接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnqsCategoryJobsUpdatesvc")
    public BaseResp vpnqsCategoryJobsUpdatesvc(@RequestBody OssVO vo){
        BaseResp baseResp=qsCategoryJobsService.updateByqsCategoryJobs(vo);
        return baseResp;
    }

    /**
     * 4.190.	省份查询接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnRmProvinceGetsvc")
    public BaseResp vpnRmProvinceGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=rmProvinceService.findByrmProvince(vo);
        return baseResp;
    }

    /**
     * 4.191.	区县查询接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnRmCountyGetsvc")
    public BaseResp vpnRmCountyGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=rmCountyService.findByrmCounty(vo);
        return baseResp;
    }

    /**
     * 4.192.	地市查询接口
     * @param vo
     * @return
     */
    @RequestMapping("/vpnRmCityGetsvc")
    public BaseResp vpnRmCityGetsvc(@RequestBody OssVO vo){
        BaseResp baseResp=rmCityService.findByrmCity(vo);
        return baseResp;
    }

    /**
     * 4.193.	专业查询接口
     * @param
     * @return
     */
    @RequestMapping("/vpnQsCategoryMajorGetsvc")
    public BaseResp vpnQsCategoryMajorGetsvc(){
        BaseResp baseResp=qsCategoryMajorService.findByqsCategoryMajor2();
        return baseResp;
    }
}
