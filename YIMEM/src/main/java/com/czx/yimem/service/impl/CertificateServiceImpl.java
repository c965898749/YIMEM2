package com.czx.yimem.service.impl;

import com.czx.yimem.dao.CertificateMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.Certificate;
import com.czx.yimem.entity.ProjectExp;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.CertificateService;
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
public class CertificateServiceImpl implements CertificateService {
    @Resource
    private CertificateMapper certificateMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Certificate record) {
        return 0;
    }

    @Override
    public int insertSelective(Certificate record) {
        return 0;
    }

    @Override
    public Certificate selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Certificate record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Certificate record) {
        return 0;
    }

    @Override
    public BaseResp findBycertificate(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Certificate certificate = JSON.toJavaObject(vo.getVpncertificateGet(), Certificate.class);
            if (certificate==null||Xtool.isNull(certificate.getUserId())|| TokenUtil.getTokenUserId().equals(certificate.getUserId())){
                return new BaseResp(Constants.FAIL_CODE, Constants.NO_AUTH);
            }
            List<Certificate> list = certificateMapper.findBycertificate(certificate);
            Page<Certificate> jobPage = (Page<Certificate>) list;
            Map map = new HashMap();
            map.put("vpncertificateGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addBycertificate(OssVO vo) {
        try {
            Certificate certificate = JSON.toJavaObject(vo.getVpncertificateAdd(), Certificate.class);
            Integer count = 0;
            if (Xtool.isNotNull(certificate.getUserId())) {
                count = certificateMapper.insertSelective(certificate);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateBycertificate(OssVO vo) {
        try {
            Certificate certificate = JSON.toJavaObject(vo.getVpncertificateUpdate(), Certificate.class);
            Integer count = 0;
            if (Xtool.isNotNull(certificate.getId())) {
                count = certificateMapper.updateByPrimaryKeySelective(certificate);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delBycertificate(OssVO vo) {
        try {
            Certificate certificate = JSON.toJavaObject(vo.getVpncertificateDel(), Certificate.class);
            Integer count = 0;
            if (Xtool.isNotNull(certificate.getId())) {
                count = certificateMapper.deleteByPrimaryKey(certificate.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
