package com.czx.yimem.service;

import com.czx.yimem.dto.MobilerVO;
import com.czx.yimem.entity.responsebean.BaseResp;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MoblieService {
    BaseResp getAuthCode(MobilerVO mobilerVO);
    BaseResp loginbyphone(MobilerVO mobilerVO);
    BaseResp weChatregistbyphone(MobilerVO mobilerVO);
    BaseResp weChatpassword(MobilerVO mobilerVO);
}
