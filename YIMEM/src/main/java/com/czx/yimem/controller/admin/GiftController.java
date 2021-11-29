package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenli
 * @createTime 2021/4/2 15:52
 */
//部门相关接口
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/admin/gift")
public class GiftController {
    @Autowired
    private GiftService giftService;


    @SysLog("获取全部可兑换的礼物")
    @PostMapping("/getList")
    @PreAuthorize("hasAnyRole('ROLE_DB_LW','ROLE_ADMIN')")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public BaseResp getList(@RequestBody OssVO vo){
        return giftService.getGiftList(vo);
    }

    @SysLog("新增/编辑礼物")
    @PostMapping("/updateGift")
    //@PreAuthorize("hasAnyRole('ROLE_AM_RY_CKBMJG','ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public BaseResp updateGift(@RequestBody OssVO vo){
        return giftService.updateGift(vo);
    }

    @SysLog("删除礼物")
    @PostMapping("/delGift")
    //@PreAuthorize("hasAnyRole('ROLE_AM_RY_CKBMJG','ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public BaseResp delGift(@RequestBody OssVO vo){
        return giftService.delGift(vo);
    }


}
