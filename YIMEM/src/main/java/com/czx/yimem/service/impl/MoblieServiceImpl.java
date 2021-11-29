package com.czx.yimem.service.impl;

import com.czx.yimem.dao.AuthorityMapper;
import com.czx.yimem.dao.JobhunterMapper;
import com.czx.yimem.dao.MemberMapper;
import com.czx.yimem.dao.UserMapper;
import com.czx.yimem.dto.MobilerVO;
import com.czx.yimem.entity.Authority;
import com.czx.yimem.entity.Jobhunter;
import com.czx.yimem.entity.Member;
import com.czx.yimem.entity.User;
import com.czx.yimem.entity.responsebean.BaseResp;
import com.czx.yimem.service.MoblieService;
import com.czx.yimem.service.admin.MembserService;
import com.czx.yimem.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
@Service
public class MoblieServiceImpl implements MoblieService {
    @Autowired
    private RedisAPI redisAPI;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private AuthorityMapper authorityMapper;
    @Autowired
    private MembserService membserService;
    @Resource
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;
    @Resource
    private JobhunterMapper jobhunterMapper;
    @Override
    public BaseResp getAuthCode(MobilerVO mobilerVO) {
        try {
            //验证手机号是否再库
            if (Xtool.isNull(mobilerVO.getPhone())){
                return new BaseResp(Constants.FAIL_CODE,"手机号"+Constants.NO_NULL);
            }
            //验证手机是否合规
            if (!PhoneUtil.regular(mobilerVO.getPhone())){
                return new BaseResp(Constants.FAIL_CODE,"手机号"+Constants.NO_STIPULATE);
            }
            String securityCode= StringUtils.Makemsgcode();
            redisAPI.set(CacheKeyManage.getVcode(mobilerVO.getPhone()),securityCode,5*60);//有效期5分钟
            log.info("使用阿里短信开始");
            NesSendAliyun.sendMes(mobilerVO.getPhone(), new String[]{securityCode,"60"},"");//阿里云平台
            log.info("使用阿里短信结束");
            return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE);
        } catch (Exception e) {
            log.error("短信发送异常"+e.getMessage());
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);
        }
    }



    @Override
    public BaseResp loginbyphone(MobilerVO mobilerVO){
        try {
            //验证手机是否合规
            if (!PhoneUtil.regular(mobilerVO.getPhone())){
                return new BaseResp(Constants.FAIL_CODE,"手机号"+Constants.NO_STIPULATE);
            }
            Object vcode= redisAPI.get(CacheKeyManage.getVcode(mobilerVO.getPhone()));
            if(StringUtil.isNullObject(vcode) || !vcode.equals(mobilerVO.getAuthCode())){
                return new BaseResp(Constants.FAIL_CODE,Constants.OVERDUE_NOTE);
            }
            String account=memberMapper.findaccountByphone(mobilerVO.getPhone());
            if (Xtool.isNull(account)){
                return new BaseResp(Constants.FAIL_CODE,Constants.NO_FOUND_PHNONE);
            }
            List<Authority> authorities=authorityMapper.selectAllByAccount(account);
            List<String> list=new ArrayList<>();
            if (Xtool.isNotNull(authorities)){
                authorities.forEach(x->{
                    list.add(x.getAuthorityCode());
                });
            }else {
                return new BaseResp(Constants.FAIL_CODE,Constants.FORBIDDEN);
            }
            String token = JwtTokenUtils.createToken(account, list, mobilerVO.getRememberMe());
            // Http Response Header 中返回 Token
//        response.setHeader(Constants.TOKEN_HEADER, token);
//        response.setContentType(Constants.CONTENT_TYPE);
            Map map=new HashMap();
            Member member = membserService.findByMember(account);
            long expiration = mobilerVO.getRememberMe() ? Constants.EXPIRATION_REMEMBER : Constants.EXPIRATION;
            Date createdDate = new Date();
            Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(expirationDate);
            member.setPassword("");
            map.put("user", member);
            map.put("overTime", dateString);
            map.put("token",token);
            return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,map);
        } catch (Exception e) {
            log.error("pc端短信异常"+e.getMessage());
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp weChatregistbyphone(MobilerVO mobilerVO){
        try {
            //先判空
            if (Xtool.isNull(mobilerVO.getPhone())||Xtool.isNull(mobilerVO.getAuthCode())||Xtool.isNull(mobilerVO.getCode())){
                return new BaseResp(Constants.FAIL_CODE,"必填字段不能为空"+Constants.NO_NULL);
            }
            //验证手机是否合规
            if (!PhoneUtil.regular(mobilerVO.getPhone())){
                return new BaseResp(Constants.FAIL_CODE,"手机号"+Constants.NO_STIPULATE);
            }
            //短信验证
            Object vcode= redisAPI.get(CacheKeyManage.getVcode(mobilerVO.getPhone()));
            if(StringUtil.isNullObject(vcode) || !vcode.equals(mobilerVO.getAuthCode())){
                return new BaseResp(Constants.FAIL_CODE,Constants.OVERDUE_NOTE);
            }
            //验证手机号是否再库
            if (userMapper.findphone(mobilerVO.getPhone())>0){
                return new BaseResp(Constants.FAIL_CODE,Constants.YE_FOUND_PHNONE);
            }
            if(Xtool.isNotNull(mobilerVO.getInviteCode()) && userMapper.findInviteCode(mobilerVO.getInviteCode())==0){
                return new BaseResp(Constants.FAIL_CODE,Constants.NO_INVIRECODE);
            }
            JSONObject SessionKeyOpenId = OpenidUtill.getSessionKeyOrOpenId(mobilerVO.getCode());
            log.info("post请求获取的SessionAndopenId=" + SessionKeyOpenId);
            String openid = SessionKeyOpenId.getString("openid");
            if (Xtool.isNull(openid)){
                return new BaseResp(Constants.FAIL_CODE,Constants.NO_FOUND_OPENID);
            }
            //验证通过注册手机号
            User user=new User();
            user.setOpenid(openid);

            String code = StringUtils.Makemsgcode();
            //生成随机名称
            user.setUserName("JHRC_"+code);
            //生成八位邀请码
            //user.setInviteCode("JH"+code);
            user.setInviteCode(getInviteCode());
            //插入被邀请码
            user.setInvitedCode(mobilerVO.getInviteCode());
            user.setPhoneNum(mobilerVO.getPhone());
            user.setCreatetime(new Date());
            userMapper.insertSelective(user);
            Jobhunter jobhunter=new Jobhunter();
            jobhunter.setUserId(user.getUserId());
            jobhunter.setName(user.getUserName());
            jobhunter.setPhone(user.getPhoneNum());
            jobhunterMapper.insertSelective(jobhunter);
            //注册完返回token
            String token=tokenService.getToken(user);
            Map map=new HashMap();
            map.put("userId",user.getUserId());
            map.put("token",token);
            return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE,map);
        } catch (Exception e) {
            log.error("微信端短信异常"+e.getMessage());
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);
        }
    }

    @Override
    public BaseResp weChatpassword(MobilerVO mobilerVO) {
        try {
            //先判空
            if (Xtool.isNull(mobilerVO.getPhone())||Xtool.isNull(mobilerVO.getAuthCode())||Xtool.isNull(mobilerVO.getPassword())){
                return new BaseResp(Constants.FAIL_CODE,"必填字段不能为空"+Constants.NO_NULL);
            }
            //验证手机是否合规
            if (!PhoneUtil.regular(mobilerVO.getPhone())){
                return new BaseResp(Constants.FAIL_CODE,"手机号"+Constants.NO_STIPULATE);
            }
            //短信验证
            Object vcode= redisAPI.get(CacheKeyManage.getVcode(mobilerVO.getPhone()));
            if(StringUtil.isNullObject(vcode) || !vcode.equals(mobilerVO.getAuthCode())){
                return new BaseResp(Constants.FAIL_CODE,Constants.OVERDUE_NOTE);
            }
            //验证手机号是否再库
            User user=userMapper.findphone2(mobilerVO.getPhone());
            if (user==null){
                return new BaseResp(Constants.FAIL_CODE,Constants.YE_FOUND_PHNONE);
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(mobilerVO.getPassword()));
            //更新密码
            userMapper.updateByPrimaryKeySelective(user);
            return new BaseResp(Constants.SUCCEE_CODE,Constants.SUCCEE);
        } catch (Exception e) {
            log.error("微信端2短信异常"+e.getMessage());
            return new BaseResp(Constants.FAIL_CODE,Constants.EXCEPTION);
        }
    }

    public String getInviteCode() {
        String inviteCode="";
        int i=0;
        while(true) {
            i++;
            inviteCode="JH"+StringUtils.Makemsgcode();
            User user=userMapper.selectByInviteCode(inviteCode);
            if(user==null||i>100) {
                break;
            }
        }
        return inviteCode.toUpperCase();
    }

}
