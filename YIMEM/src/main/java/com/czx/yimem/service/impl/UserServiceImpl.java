package com.czx.yimem.service.impl;

import com.czx.yimem.dao.*;
import com.czx.yimem.dto.OssVO;
import com.czx.yimem.dto.PageVO;
import com.czx.yimem.entity.*;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.JobWantedService;
import com.czx.yimem.service.UserService;
import com.czx.yimem.utils.Constants;
import com.czx.yimem.utils.UploadUtil;
import com.czx.yimem.utils.Xtool;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private JobWantedMapper jobWantedMapper;
    @Resource
    private JobExpMapper jobExpMapper;
    @Resource
    private EducationExpMapper educationExpMapper;
    @Resource
    private LanguageMapper languageMapper;
    @Resource
    private ProjectExpMapper projectExpMapper;
    @Resource
    private CertificateMapper certificateMapper;
    @Resource
    private JobhunterMapper jobhunterMapper;
    @Resource
    private DistributionMapper distributionMapper;

    @Override
    public List<User> selectByUser(User user) {
        return userMapper.selectByUser(user);
    }

    @Override
    public Integer findphone(String phone) {
        return userMapper.findphone(phone);
    }

    @Override
    public User findphone2(String phone) {
        return userMapper.findphone2(phone);
    }

    @Override
    public Integer findByuserCount(Integer userId) {
        return userMapper.findByuserCount(userId);
    }

    @Override
    public String findpasswordByuserId(Integer userId) {
        return userMapper.findpasswordByuserId(userId);
    }

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User selectByopenid(String openid) {
        return userMapper.selectByopenid(openid);
    }

    @Override
    public BaseResp findByuser(OssVO vo) {
        try {
            User user = JSON.toJavaObject(vo.getVpnuserGet(), User.class);
            if (Xtool.isNull(user.getUserId())) {
                return new BaseResp(Constants.FAIL_CODE, "用户标识" + Constants.NO_NULL);
            }
//            List<User> list = userMapper.selectByUser(user);
            User x = userMapper.findByuserId(user.getUserId());
            //清除支付密码
            x.setPassword("");
            x.setOpenid("");
            x.setIdentityNum("");
//                    4.5.5.	jobWanted
            List<JobWanted> jobWanteds = jobWantedMapper.findByjobWanted(new JobWanted(x.getUserId()));
            x.setJobWanteds(jobWanteds);
//                    4.5.6.	educationExp
            List<EducationExp> educationExps = educationExpMapper.findByeducationExp(new EducationExp(x.getUserId()));
            x.setEducationExps(educationExps);
//                    4.5.7.	jobExp
            List<JobExp> jobExps = jobExpMapper.findByjobExp(new JobExp(x.getUserId()));
            x.setJobExps(jobExps);
//                    4.5.8.	language
            List<Language> languages = languageMapper.findBylanguage(new Language(x.getUserId()));
            x.setLanguages(languages);
//                    4.5.9.	projectExp
            List<ProjectExp> projectExps = projectExpMapper.findByprojectExp(new ProjectExp(x.getUserId()));
            x.setProjectExps(projectExps);
//                    4.5.10.	certificate
            List<Certificate> certificates = certificateMapper.findBycertificate(new Certificate(x.getUserId()));
            x.setCertificates(certificates);
            Map map = new HashMap();
            map.put("vpnuserGetResp", x);
            return new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp vpncompanydeliverUserDetailGetsvc(Integer id) {

        return null;
    }

    @Override
    public BaseResp addByuser(OssVO vo) {
        try {
            User user = JSON.toJavaObject(vo.getVpnuserAdd(), User.class);
            Integer count = 0;
            if (Xtool.isNotNull(user.getOpenid()) && Xtool.isNotNull(user.getPhoneNum())) {
//                验证手机号和微信号唯一
                if (userMapper.selectByopenid(user.getOpenid()) == null && Xtool.isNull(userMapper.selectByUser(new User(user.getPhoneNum())))) {
                    count = userMapper.insertSelective(user);
                    Jobhunter jobhunter=new Jobhunter();
                    jobhunter.setUserId(user.getUserId());
                    jobhunter.setName(user.getUserName());
                    jobhunter.setPhone(user.getPhoneNum());
                    jobhunterMapper.insertSelective(jobhunter);
                } else {
                    return new BaseResp(Constants.FAIL_CODE, Constants.YE_FOUND_USER);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp udateByuser(OssVO vo) {
        try {
            User user = JSON.toJavaObject(vo.getVpnuserUpdate(), User.class);
            Integer count = 0;
            if (Xtool.isNotNull(user.getUserId())) {
//                清除认证数据
                user.setOpenid("");
                user.setPhoneNum("");
                //查询图片
                User oldUser = userMapper.findByuserId(user.getUserId());
                //更新图片时删除旧图片
                if(user.getHeadImg()!=null && oldUser.getHeadImg()!=null && !oldUser.getHeadImg().equals(user.getHeadImg())){
                    UploadUtil.deleteFile(oldUser.getHeadImg(),"ax-jhrc");
                }

                count = userMapper.updateByPrimaryKeySelective(user);
                if (count>0&&Xtool.isNotNull(user.getUserName())){
                    Jobhunter jobhunter=new Jobhunter();
                    jobhunter.setUserId(user.getUserId());
                    jobhunter.setName(user.getUserName());
                    jobhunterMapper.updateByUserId(jobhunter);
                }
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp delByuser(OssVO vo) {
        try {
            User user = JSON.toJavaObject(vo.getVpnuserDel(), User.class);
            Integer count = 0;
            if (Xtool.isNotNull(user.getUserId())) {
                count = userMapper.deleteByPrimaryKey(user.getUserId());
            }
            return count > 0 ? new BaseResp(Constants.SUCCEE_CODE, Constants.SUCCEE) : new BaseResp(Constants.FAIL_CODE, Constants.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE, Constants.EXCEPTION);
        }
    }

    @Override
    public User findByuserId(Integer id) {
        User user=userMapper.findByuserId(id);
        //查看简历是否完善
        user.setDevIsComplete(0);
        user.setIsdistribution(0);
        if (jobExpMapper.findByjobExpCount(id)>0&&jobWantedMapper.findBywantedCount(id)>0&&educationExpMapper.findByeducationExpCount(id)>0){
            user.setDevIsComplete(1);
        }
        //查看是否是分销者
        if (distributionMapper.isdistribution(id)>0){
            user.setIsdistribution(1);
        }
        return user;
    }

    @Override
    public BaseResp vpnuserbasicDeli(Integer id) {
        try {
            return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,userMapper.vpnuserbasicDeli(id));
        } catch (Exception e) {
            return new BaseResp(Constants.FAIL_CODE,Constants.FAIL);
        }
    }

    @Override
    public BaseResp inviteList(String  inviteCode) {
        try {
            return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,userMapper.inviteList(inviteCode));
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp(Constants.FAIL_CODE,Constants.FAIL);
        }
    }
}
