package com.czx.yimem.service.admin.impl;

import com.czx.yimem.dao.NavigationMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.JobExp;
import com.czx.yimem.entity.Navigation;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.admin.NavigationService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class NavigationServiceImpl implements NavigationService {
    @Resource
    private NavigationMapper navigationMapper;

    @Override
    public int deleteByPrimaryKey(Integer naId) {
        return 0;
    }

    @Override
    public int insert(Navigation record) {
        return 0;
    }

    @Override
    public int insertSelective(Navigation record) {
        return 0;
    }

    @Override
    public Navigation selectByPrimaryKey(Integer naId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Navigation record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Navigation record) {
        return 0;
    }

    @Override
    public BaseResp getNavigation(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            Navigation navigation = JSON.toJavaObject(vo.getVpnnavigationGet(), Navigation.class);
            List<Navigation> list = navigationMapper.findBynavigation(navigation);
            Page<Navigation> jobPage = (Page<Navigation>) list;
            Map map = new HashMap();
            map.put("content", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp getNavigation2(OssVO ossVO) {
        try {
            List<Navigation> list = navigationMapper.findBynavigation(new Navigation());
            Map map = new HashMap();
            map.put("content", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delNavigation(OssVO vo) {
        try {
            Navigation navigation = JSON.toJavaObject(vo.getVpnnavigationDel(), Navigation.class);
            Integer count = 0;
            if (Xtool.isNotNull(navigation.getNaId())) {
                count = navigationMapper.deleteByPrimaryKey(navigation.getNaId());
                //TODO 删除图片接口
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp addNavigation(OssVO vo) {
        try {
            Navigation navigation = JSON.toJavaObject(vo.getVpnnavigationAdd(), Navigation.class);
            Integer count = 0;
            if (Xtool.isNull(navigation.getIconUrl())&&Xtool.isNull(navigation.getName())) {
               return new BaseResp(Constants.FAIL_CODE,"图标或名称"+Constants.NO_NULL);
            }
            if (navigationMapper.selectcount()>=Constants.EN_Nav_COUNT){
                return new BaseResp(Constants.FAIL_CODE,"导航最大数量不能超过"+Constants.EN_Nav_COUNT+"个");
            }
            count = navigationMapper.insertSelective(navigation);
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateNavigation(OssVO vo) {
        try {
            Navigation navigation = JSON.toJavaObject(vo.getVpnnavigationUpdate(), Navigation.class);
            Integer count = 0;
            if (Xtool.isNotNull(navigation.getNaId())) {
                if ("2".equals(navigation.getState())){
                    Date date=new Date();
                    navigation.setUpdateTime(date);
                    navigationMapper.updateotherstate(navigation.getNaId());
                }
                count = navigationMapper.updateByPrimaryKeySelective(navigation);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
