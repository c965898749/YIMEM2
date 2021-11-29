package com.czx.yimem.service.impl;

import com.czx.yimem.dao.ProjectExpMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Language;
import com.czx.yimem.entity.ProjectExp;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.ProjectExpService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.TokenUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class ProjectExpServiceImpl implements ProjectExpService {
    @Resource
    private ProjectExpMapper projectExpMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(ProjectExp record) {
        return 0;
    }

    @Override
    public int insertSelective(ProjectExp record) {
        return 0;
    }

    @Override
    public ProjectExp selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ProjectExp record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ProjectExp record) {
        return 0;
    }

    @Override
    public BaseResp findByprojectExp(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            ProjectExp projectExp = JSON.toJavaObject(vo.getVpnprojectExpGet(), ProjectExp.class);
            if (projectExp==null||Xtool.isNull(projectExp.getUserId())|| TokenUtil.getTokenUserId().equals(projectExp.getUserId())){
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
            }
            List<ProjectExp> list = projectExpMapper.findByprojectExp(projectExp);
            Page<ProjectExp> jobPage = (Page<ProjectExp>) list;
            Map map = new HashMap();
            map.put("vpnprojectExpGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addByprojectExp(OssVO vo) {
        try {
            ProjectExp projectExp = JSON.toJavaObject(vo.getVpnprojectExpAdd(), ProjectExp.class);
            Integer count = 0;
            if (Xtool.isNotNull(projectExp.getUserId())) {
                if (projectExp==null||Xtool.isNull(projectExp.getUserId())|| TokenUtil.getTokenUserId().equals(projectExp.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = projectExpMapper.insertSelective(projectExp);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateByprojectExp(OssVO vo) {
        try {
            ProjectExp projectExp = JSON.toJavaObject(vo.getVpnprojectExpUpdate(), ProjectExp.class);
            Integer count = 0;
            if (Xtool.isNotNull(projectExp.getId())) {
                if (projectExp==null||Xtool.isNull(projectExp.getUserId())|| TokenUtil.getTokenUserId().equals(projectExp.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = projectExpMapper.updateByPrimaryKeySelective(projectExp);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delByprojectExp(OssVO vo) {
        try {
            ProjectExp projectExp = JSON.toJavaObject(vo.getVpnprojectExpDel(), ProjectExp.class);
            Integer count = 0;
            if (Xtool.isNotNull(projectExp.getId())) {
                if (projectExp==null||Xtool.isNull(projectExp.getUserId())|| TokenUtil.getTokenUserId().equals(projectExp.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = projectExpMapper.deleteByPrimaryKey(projectExp.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
