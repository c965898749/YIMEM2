package com.czx.yimem.service.admin;

import com.czx.yimem.dto.AuthorityOV;
import com.czx.yimem.dto.AuthorityVO;
import com.czx.yimem.dto.RoleDTO;
import com.czx.yimem.dto.RoleVO;
import com.czx.yimem.entity.Role;
import com.czx.yimem.entity.responsebean.BaseResp;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RoleService {
    BaseResp search(RoleDTO dto);

    BaseResp create(RoleVO vo);

    BaseResp update(RoleVO vo);

    BaseResp getAuthority(Integer roleId);

    BaseResp setAuth(int id, AuthorityOV authorityOV);

    List<Role> selectByMemberId(Integer mid);

    BaseResp delete(Integer id);
}
