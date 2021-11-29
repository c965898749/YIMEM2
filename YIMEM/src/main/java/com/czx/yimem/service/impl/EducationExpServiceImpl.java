package com.czx.yimem.service.impl;

import com.czx.yimem.dao.EducationExpMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.EducationExp;
import com.czx.yimem.entity.JobWanted;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.EducationExpService;
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
public class EducationExpServiceImpl implements EducationExpService {
    @Resource
    private EducationExpMapper educationExpMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(EducationExp record) {
        return 0;
    }

    @Override
    public int insertSelective(EducationExp record) {
        return 0;
    }

    @Override
    public EducationExp selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(EducationExp record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(EducationExp record) {
        return 0;
    }

    @Override
    public BaseResp findByeducationExp(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            EducationExp educationExp = JSON.toJavaObject(vo.getVpneducationExpGet(), EducationExp.class);
            if (educationExp==null||Xtool.isNull(educationExp.getUserId())|| TokenUtil.getTokenUserId().equals(educationExp.getUserId())){
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
            }
            List<EducationExp> list = educationExpMapper.findByeducationExp(educationExp);
            Page<EducationExp> jobPage = (Page<EducationExp>) list;
            Map map = new HashMap();
            map.put("vpneducationExpGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addByeducationExp(OssVO vo) {
        try {
            EducationExp educationExp = JSON.toJavaObject(vo.getVpneducationExpAdd(), EducationExp.class);
            Integer count = 0;
            if (Xtool.isNotNull(educationExp.getUserId())) {
                if (educationExp==null||Xtool.isNull(educationExp.getUserId())|| TokenUtil.getTokenUserId().equals(educationExp.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = educationExpMapper.insertSelective(educationExp);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateByeducationExp(OssVO vo) {
        try {
            EducationExp educationExp = JSON.toJavaObject(vo.getVpneducationExpUpdate(), EducationExp.class);
            Integer count = 0;
            if (Xtool.isNotNull(educationExp.getId())) {
                if (educationExp==null||Xtool.isNull(educationExp.getUserId())|| TokenUtil.getTokenUserId().equals(educationExp.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = educationExpMapper.updateByPrimaryKeySelective(educationExp);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delByeducationExp(OssVO vo) {
        try {
            EducationExp educationExp = JSON.toJavaObject(vo.getVpneducationExpDel(), EducationExp.class);
            Integer count = 0;
            if (Xtool.isNotNull(educationExp.getId())) {
                if (educationExp==null||Xtool.isNull(educationExp.getUserId())|| TokenUtil.getTokenUserId().equals(educationExp.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = educationExpMapper.deleteByPrimaryKey(educationExp.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
