package com.czx.yimem.service.admin;

import com.czx.yimem.dto.MemberDTO;
import com.czx.yimem.dto.MemberVO;
import com.czx.yimem.entity.Member;
import com.czx.yimem.entity.responsebean.BaseResp;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MembserService {


    BaseResp create(MemberVO member);

    BaseResp search(MemberDTO dto);

    BaseResp update(MemberVO member);

    void enable(MemberVO member);

    void disenable(MemberVO member);

    Member findByMember(String account);

    BaseResp delete(MemberVO member);

    String getName(String account);

    /**
     * 手机验证
     */

}
