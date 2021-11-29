package com.czx.yimem.controller.admin;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.dto.DepartmentVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description : PC端部门相关接口
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */

//部门相关接口
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/admin/department")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;


    //获取整个部门树,不包含作废的
    @SysLog("获取整个部门树")
    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_CKBMJG','ROLE_ADMIN')")
    public BaseResp get(){
        return departmentService.getAll();
    }

    @SysLog("获取单个部门树")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_CKBMJG','ROLE_ADMIN')")
    public BaseResp getByid(@PathVariable()int id){
        return departmentService.getByid(id);
    }

//    @GetMapping("/ssssss/{id}")
//    public String ssssss(@PathVariable()int id){
//        return departmentService.getAllBYid(id);
//    }

    //创建部门
    @SysLog("创建部门")
    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_XZBM','ROLE_ADMIN')")
    public BaseResp create(@RequestBody DepartmentVO depart){
        return departmentService.create(depart);

    }

    //修改部门
    @SysLog("修改部门")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_XGBM','ROLE_ADMIN')")
    public BaseResp update(@PathVariable()int id,@RequestBody DepartmentVO depart){
        return departmentService.update(id,depart);
    }

    //删除部门
    @SysLog("删除部门")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_AM_RY_SCBM','ROLE_ADMIN')")
    public BaseResp<String> delete(@PathVariable int id){
        return departmentService.delete(id);
    }

}
