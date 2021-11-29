package com.czx.yimem.service.impl;

import com.czx.yimem.dao.LanguageMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.JobWanted;
import com.czx.yimem.entity.Language;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.LanguageService;
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
public class LanguageServiceImpl implements LanguageService {
    @Resource
    private LanguageMapper languageMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Language record) {
        return 0;
    }

    @Override
    public int insertSelective(Language record) {
        return 0;
    }

    @Override
    public Language selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Language record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Language record) {
        return 0;
    }

    @Override
    public BaseResp findBylanguage(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Language language = JSON.toJavaObject(vo.getVpnlanguageGet(), Language.class);
            if (language==null||Xtool.isNull(language.getUserId())|| TokenUtil.getTokenUserId().equals(language.getUserId())){
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
            }
            List<Language> list = languageMapper.findBylanguage(language);
            Page<Language> jobPage = (Page<Language>) list;
            Map map = new HashMap();
            map.put("vpnlanguageGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addBylanguage(OssVO vo) {
        try {
            Language language = JSON.toJavaObject(vo.getVpnlanguageAdd(), Language.class);
            Integer count = 0;
            if (Xtool.isNotNull(language.getUserId())) {
                if (language==null||Xtool.isNull(language.getUserId())|| TokenUtil.getTokenUserId().equals(language.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = languageMapper.insertSelective(language);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateBylanguage(OssVO vo) {
        try {
            Language language = JSON.toJavaObject(vo.getVpnlanguageUpdate(), Language.class);
            Integer count = 0;
            if (Xtool.isNotNull(language.getId())) {
                if (language==null||Xtool.isNull(language.getUserId())|| TokenUtil.getTokenUserId().equals(language.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = languageMapper.updateByPrimaryKeySelective(language);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delBylanguage(OssVO vo) {
        try {
            Language language = JSON.toJavaObject(vo.getVpnlanguageDel(), Language.class);
            Integer count = 0;
            if (Xtool.isNotNull(language.getId())) {
                if (language==null||Xtool.isNull(language.getUserId())|| TokenUtil.getTokenUserId().equals(language.getUserId())){
                    return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
                }
                count = languageMapper.deleteByPrimaryKey(language.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
