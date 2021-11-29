package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dto.MemberDTO;
import com.czx.yimem.dto.MemberVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.MembserService;
import com.czx.yimem.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @Description :PC 端职员相关接口
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
//职员管理
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/admin/staff")
public class StaffController {

    @Autowired
    private MembserService membserService;

    //分页获取或查询职员
    @SysLog("分页获取或查询职员")
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_TYZY','ROLE_AM_RY_DELL','ROLE_AM_RY_EDIT','ROLE_AM_RY_ADD','ROLE_AM_RY','ROLE_ADMIN')")
    @PostMapping("/search")
    public BaseResp<Page<MemberVO>> get(@RequestBody MemberDTO dto){
        return membserService.search(dto);

    }

    //添加职员
    @SysLog("添加职员")
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_ADD','ROLE_ADMIN')")
    @PostMapping("/create")
    public BaseResp<MemberVO> create(@RequestBody MemberVO member){
        return membserService.create(member);
    }

    //修改职员信息
    @SysLog("修改职员信息")
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_EDIT','ROLE_ADMIN')")
    @PostMapping("/update")
    public BaseResp<MemberVO> update(@RequestBody MemberVO member){
        return membserService.update(member);
    }

    //删除职员??需要吗？
    @SysLog("删除职员??需要吗？")
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_DELL','ROLE_ADMIN')")
    @PostMapping("/delete")
    public BaseResp delete(@RequestBody MemberVO member){
        return membserService.delete(member);
    }


    //停用 启用
    @SysLog("停用 启用")
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_TYZY','ROLE_ADMIN')")
    @PostMapping("/state")
    public BaseResp<String> enableOrDisenable(@RequestBody MemberVO member){
        if(member.getState().equals("1")){//启用
            membserService.enable(member);
        }else if(member.getState().equals("0")){//停用
            membserService.disenable(member);
        }
        return new BaseResp<>(Constants.SUCCEE_CODE,Constants.SUCCEE);
    }


}
