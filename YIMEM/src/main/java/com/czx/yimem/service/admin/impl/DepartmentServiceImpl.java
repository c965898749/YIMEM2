package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.GroupMemberMapper;
import com.czx.yimem.dao.MemberMapper;
import com.czx.yimem.dto.DepartmentVO;
import com.czx.yimem.dao.DepartmentMapper;
import com.czx.yimem.dto.MemberOV;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.dto.TreeDataVO;
import com.czx.yimem.entity.Beginner;
import com.czx.yimem.entity.Department;
import com.czx.yimem.entity.Member;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.DepartmentService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private GroupMemberMapper groupMemberMapper;
    @Resource
    private MemberMapper memberMapper;

    @Override
    public String getAllBYid(Integer id) {
        try {

            List<Integer> all = departmentMapper.getAllBYid(id);
            StringBuffer s=new StringBuffer();
            s.append(id);
            s.append(kk(all));
            return s.toString().replaceAll(",,",",");
        } catch (BeansException e) {
            return null;
        }

    }
    private String kk(List<Integer> list) {
        StringBuffer a=new StringBuffer();
        for (Integer item : list) {
            a.append(","+item);
            List<Integer> all = departmentMapper.getAllBYid(item);
            if (Xtool.isNotNull(all)){
                a.append(","+kk(all));
            }
        }
        return a.toString();
    }

    @Override
    public BaseResp getAll() {
        try {
            List<Department> all = departmentMapper.selectAllByState("1");
            if (Xtool.isNull(all)) {
                return new BaseResp(Constants.SUCCEE_CODE, Constants.NOT_FOUND);
            }
            List<TreeDataVO> rt = new ArrayList<>();
            //组织成树状结构
            //组织根节点
            for (Department item : all) {
                if (Xtool.isNull(item.getfDepId())) {
                    TreeDataVO vo = new TreeDataVO();
                    vo.setKey(item.getDepId());
                    vo.setValue(item.getDepId());
                    vo.setTitle(item.getName());
                    vo.setChildren(new ArrayList<>());
                    vo.setChildren(findChildren(vo, all));
                    rt.add(vo);
                }
            }
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, rt);
        } catch (BeansException e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp getByid(int id) {
        try {
            Department department=departmentMapper.selectByPrimaryKey(id);
            Member member=memberMapper.selectByPrimaryKey(department.getmUserId());
            MemberOV ov=new MemberOV();
            if (member!=null){
                BeanUtils.copyProperties(member, ov);
            }
            department.setManager(ov);
            department.setFdep(new Department());
            if (Xtool.isNotNull(department.getfDepId())){
                department.setFdep(new Department());
                Department de=departmentMapper.selectByPrimaryKey(department.getfDepId());
                department.setFdep(de);
                Member m=memberMapper.selectByPrimaryKey(department.getmUserId());
                MemberOV o=new MemberOV();
                if (m!=null){
                    BeanUtils.copyProperties(m, o);
                }
                department.setManager(o);
            }
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, department);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    private List<TreeDataVO> findChildren(TreeDataVO p, List<Department> list) {
        for (Department item : list) {
            if (item.getfDepId() != null && item.getfDepId().equals(p.getKey())) {
                TreeDataVO vo = new TreeDataVO();
                vo.setChildren(new ArrayList<>());
                vo.setTitle(item.getName());
                vo.setKey(item.getDepId());
                vo.setValue(item.getDepId());
                p.getChildren().add(vo);
            }
        }
        if (p.getChildren().size() == 0) {
            return p.getChildren();
        } else {
            for (TreeDataVO c : p.getChildren()) {
                c.setChildren(findChildren(c, list));
            }
            return p.getChildren();
        }

    }

    @Override
    public BaseResp create(DepartmentVO depart) {
        try {
            Department item = new Department();
            BeanUtils.copyProperties(depart, item);
            item.setState("1");
            item.setmUserId(depart.getManager().getId());
//            item.setfDepId(depart.getfDep().getDepId());
            departmentMapper.insert(item);
            depart.setDepId(item.getDepId());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, depart);
        } catch (BeansException e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        }
    }

    @Override
    public BaseResp update(int id, DepartmentVO depart) {
        try {
            Department old = departmentMapper.selectByPrimaryKey(id);
            old.setName(depart.getName());
            if (depart.getManager() != null) {
                old.setmUserId(depart.getManager().getId());
            }
            if (depart.getfDep() != null) {
                old.setfDepId(depart.getfDepId());
            }

            if (depart.getName() != null) {
                old.setName(depart.getName());
            }
            departmentMapper.updateByPrimaryKeySelective(old);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delete(int id) {
        try {
            Department old = departmentMapper.selectByPrimaryKey(id);
            if (old == null) {
                return new BaseResp(Constants.FAIL_CODE, Constants.NOT_FOUND);
            }
//        存在子部门时候不可删除
            if (departmentMapper.selectchild(id) > 0) {
                return new BaseResp(Constants.FAIL_CODE, Constants.YE_GROUP);
            }
//        存在员工时候不可删除
            if (groupMemberMapper.selectBychild(id) > 0) {
                return new BaseResp(Constants.FAIL_CODE, Constants.YE_MEMBER);
            }
            old.setState("0");
            departmentMapper.updateByPrimaryKeySelective(old);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE);
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        }
    }


}
