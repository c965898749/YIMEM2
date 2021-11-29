package com.czx.yimem.service.impl;

import com.czx.yimem.dao.QsCategoryJobsMapper;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.dto.TreeData2VO;
import com.czx.yimem.dto.TreeDataVO;
import com.czx.yimem.entity.Department;
import com.czx.yimem.entity.JobCategory;
import com.czx.yimem.entity.QsCategory;
import com.czx.yimem.entity.QsCategoryJobs;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.JobCategoryService;
import com.czx.yimem.service.QsCategoryJobsService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class QsCategoryJobsServiceImpl implements QsCategoryJobsService {

    @Resource
    private QsCategoryJobsMapper qsCategoryJobsMapper;
    @Autowired
    private JobCategoryService jobCategoryService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(QsCategoryJobs record) {
        return 0;
    }

    @Override
    public int insertSelective(QsCategoryJobs record) {
        return 0;
    }

    @Override
    public QsCategoryJobs selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(QsCategoryJobs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(QsCategoryJobs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(QsCategoryJobs record) {
        return 0;
    }

    @Override
    public BaseResp findByqsCategoryJobs(OssVO vo) {
        try {
            PageVO pageVO = JSON.toJavaObject(vo.getSvcCaller(), PageVO.class);
            PageHelper.startPage(pageVO.getPage(), pageVO.getPageSize());
            QsCategoryJobs qsCategoryJobs = JSON.toJavaObject(vo.getVpnqsCategoryJobsGet(), QsCategoryJobs.class);
            List<QsCategoryJobs> list = qsCategoryJobsMapper.findByqsCategoryJobs(qsCategoryJobs);
            Page<QsCategoryJobs> jobPage = (Page<QsCategoryJobs>) list;
            list.forEach(x -> {
                JobCategory jobCategory = jobCategoryService.selectByPrimaryKey(x.getId());
                if (jobCategory != null) {
                    x.setMoney(jobCategory.getMoney());
                    x.setBrokerage(jobCategory.getBrokerage());
                }
            });
            Map map = new HashMap();
            map.put("vpnqsCategoryJobsGetResp", list);
            map.put("totalElements", jobPage.getTotal());
            map.put("totalPages", jobPage.getPages());
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    /**
     * 树形结构
     * @param
     * @return
     */
    @Override
    public BaseResp findByqsCategoryJobs2() {
        try {
//            所有数据
            List<QsCategoryJobs> all = qsCategoryJobsMapper.selecAll();
            List<TreeDataVO> rt = new ArrayList<>();
            //组织成树状结构
            //组织根节点
//            List<QsCategoryJobs> list = qsCategoryJobsMapper.findByqsCategoryJobs(qsCategoryJobs);
            for (QsCategoryJobs item : all) {
                if (item.getParentid()==0) {
                    TreeDataVO vo = new TreeDataVO();
                    vo.setKey(item.getId());
                    vo.setValue(item.getId());
                    vo.setTitle(item.getCategoryname());
                    vo.setBrokerage(item.getBrokerage());
                    vo.setMoney(item.getMoney());
                    vo.setChildren(new ArrayList<>());
                    vo.setChildren(findChildren(vo, all));
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

    @Override
    public BaseResp findByqsCategoryJobs3() {
        try {
//            所有数据
            List<QsCategoryJobs> all = qsCategoryJobsMapper.selecAll();
            List<TreeData2VO> rt = new ArrayList<>();
            //组织成树状结构
            //组织根节点
//            List<QsCategoryJobs> list = qsCategoryJobsMapper.findByqsCategoryJobs(qsCategoryJobs);
            for (QsCategoryJobs item : all) {
                if (item.getParentid()==0) {
                    TreeData2VO vo = new TreeData2VO();
                    vo.setId(item.getId());
                    vo.setLabel(item.getCategoryname());
                    vo.setBrokerage(item.getBrokerage());
                    vo.setMoney(item.getMoney());
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

    private List<TreeData2VO> findChildren3(TreeData2VO p, List<QsCategoryJobs> list) {
        for (QsCategoryJobs item : list) {
            if (item.getId() != null && (item.getParentid()+"").equals(p.getId()+"")) {
                TreeData2VO vo = new TreeData2VO();
                vo.setChildren(new ArrayList<>());
                vo.setLabel(item.getCategoryname());
                vo.setId(item.getId());
                vo.setBrokerage(item.getBrokerage());
                vo.setMoney(item.getMoney());
                p.getChildren().add(vo);
            }
        }
        if (p.getChildren().size() == 0) {
            return p.getChildren();
        } else {
            for (TreeData2VO c : p.getChildren()) {
                c.setChildren(findChildren3(c, list));
            }
            return p.getChildren();
        }

    }


    private List<TreeDataVO> findChildren(TreeDataVO p, List<QsCategoryJobs> list) {
        for (QsCategoryJobs item : list) {
            if (item.getId() != null && (item.getParentid()+"").equals(p.getKey()+"")) {
                TreeDataVO vo = new TreeDataVO();
                vo.setChildren(new ArrayList<>());
                vo.setTitle(item.getCategoryname());
                vo.setKey(item.getId());
                vo.setValue(item.getId());
                vo.setBrokerage(item.getBrokerage());
                vo.setMoney(item.getMoney());
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
    public BaseResp addByqsCategoryJobs(OssVO vo) {
        try {
            QsCategoryJobs qsCategoryJobs = JSON.toJavaObject(vo.getVpnqsCategoryJobsAdd(), QsCategoryJobs.class);
            Integer count = qsCategoryJobsMapper.insertSelective(qsCategoryJobs);
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delByqsCategoryJobs(OssVO vo) {
        try {
            QsCategoryJobs qsCategoryJobs = JSON.toJavaObject(vo.getVpnqsCategoryJobsDel(), QsCategoryJobs.class);
            Integer count = 0;
            if (Xtool.isNotNull(qsCategoryJobs.getId())) {
                count = qsCategoryJobsMapper.deleteByPrimaryKey(qsCategoryJobs.getId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp updateByqsCategoryJobs(OssVO vo) {
        try {
            QsCategoryJobs qsCategoryJobs = JSON.toJavaObject(vo.getVpnqsCategoryJobsUpdate(), QsCategoryJobs.class);
            Integer count = 0;
            if (Xtool.isNotNull(qsCategoryJobs.getId())) {
                count = qsCategoryJobsMapper.updateByPrimaryKeySelective(qsCategoryJobs);
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }
}
