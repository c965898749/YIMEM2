package com.czx.yimem.service.impl;

import com.czx.yimem.dao.QsCategoryMajorMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.dto.TreeData2VO;
import com.czx.yimem.dto.TreeData3VO;
import com.czx.yimem.entity.QsCategoryJobs;
import com.czx.yimem.entity.QsCategoryMajor;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.QsCategoryMajorService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class QsCategoryMajorServiceImpl implements QsCategoryMajorService {
    @Resource
    private QsCategoryMajorMapper qsCategoryMajorMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(QsCategoryMajor record) {
        return 0;
    }

    @Override
    public int insertSelective(QsCategoryMajor record) {
        return 0;
    }

    @Override
    public QsCategoryMajor selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(QsCategoryMajor record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(QsCategoryMajor record) {
        return 0;
    }

    @Override
    public BaseResp findByqsCategoryMajor(OssVO vo) {
        try {
            QsCategoryMajor qsCategoryMajor = JSON.toJavaObject(vo.getVpnQsCategoryMajorGet(), QsCategoryMajor.class);
            List<QsCategoryMajor> list = qsCategoryMajorMapper.findByqsCategoryMajor(qsCategoryMajor);
            Map map = new HashMap();
            map.put("vpnQsCategoryMajorGetResp", list);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp findByqsCategoryMajor2() {
        try {
//            所有数据
            List<QsCategoryMajor> all = qsCategoryMajorMapper.findAll();
            List<TreeData3VO> rt = new ArrayList<>();
            //组织成树状结构
            //组织根节点
//            List<QsCategoryJobs> list = qsCategoryJobsMapper.findByqsCategoryJobs(qsCategoryJobs);
            for (QsCategoryMajor item : all) {
                if (item.getParentid()==0) {
                    TreeData3VO vo = new TreeData3VO();
                    vo.setId(item.getId());
                    vo.setLabel(item.getCategoryname());
                    vo.setChildren(new ArrayList<>());
                    vo.setChildren(findChildren3(vo, all));
                    rt.add(vo);
                }
            }
            Map map = new HashMap();
            map.put("vpnqsCategoryJobsGetResp", rt);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    private List<TreeData3VO> findChildren3(TreeData3VO p, List<QsCategoryMajor> list) {
        for (QsCategoryMajor item : list) {
            if (item.getId() != null && (item.getParentid()+"").equals(p.getId()+"")) {
                TreeData3VO vo = new TreeData3VO();
                vo.setChildren(new ArrayList<>());
                vo.setLabel(item.getCategoryname());
                vo.setId(item.getId());
                p.getChildren().add(vo);
            }
        }
        if (p.getChildren().size() == 0) {
            return p.getChildren();
        } else {
            for (TreeData3VO c : p.getChildren()) {
                c.setChildren(findChildren3(c, list));
            }
            return p.getChildren();
        }

    }
}
