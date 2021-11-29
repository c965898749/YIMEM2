package com.czx.yimem.controller;

import com.czx.yimem.dto.MobilerVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.MoblieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
/**
 * 短信验证
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("auth")
@RestController
public class MobileController {
    @Autowired
    private MoblieService moblieService;

    /**
     * 短信获取
     * @param mobilerVO
     * @return
     */
    @RequestMapping(value = "/getnote")
    public BaseResp getMessage(@RequestBody MobilerVO mobilerVO) {
        return moblieService.getAuthCode(mobilerVO);
    }


    /**
     * pc短信验证
     * @param mobilerVO
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/loginbyphone")
    public BaseResp loginbyphone(@RequestBody MobilerVO mobilerVO){
        return moblieService.loginbyphone(mobilerVO);
    }

    /**
     * 微信短信验证
     * @param mobilerVO
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/weChatregistbyphone")
    public BaseResp weChatregistbyphone(@RequestBody MobilerVO mobilerVO){
        return moblieService.weChatregistbyphone(mobilerVO);
    }

    /**
     * 微信设置提现密码
     * @param mobilerVO
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/weChatpassword")
    public BaseResp weChatpassword(@RequestBody MobilerVO mobilerVO){
        return moblieService.weChatpassword(mobilerVO);
    }
}
