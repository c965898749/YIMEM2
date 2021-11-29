package com.czx.yimem.service.impl;

import com.czx.yimem.dao.LsImgMapper;
import com.czx.yimem.entity.LsImg;
import com.czx.yimem.service.LsImgService;
import com.czx.yimem.utils.UploadUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Service
public class LsImgServiceImpl implements LsImgService {
    @Resource
    private LsImgMapper lsImgMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(LsImg record) {
        return 0;
    }

    @Override
    public int insertSelective(LsImg record) {
        return 0;
    }

    @Override
    public LsImg selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(LsImg record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(LsImg record) {
        return 0;
    }

    @Override
    public void updatefindTimeUp() {
        try {
            List<LsImg> list =lsImgMapper.updatefindTimeUp();
            list.forEach(x->{
                lsImgMapper.deleteByPrimaryKey(x.getId());
                UploadUtil.deleteObject("ax-jhrc",x.getRealPath());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
