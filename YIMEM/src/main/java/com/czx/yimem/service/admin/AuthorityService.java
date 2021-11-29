package com.czx.yimem.service.admin;

import com.czx.yimem.dto.AuthorityVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AuthorityService {



       BaseResp getUserAuthority(String account);
       BaseResp getAll();
       BaseResp getUserMenu(String account);
}
