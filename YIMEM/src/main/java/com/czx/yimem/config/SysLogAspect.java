package com.czx.yimem.config;

import com.czx.yimem.Annotation.SysLog;
import com.czx.yimem.entity.LogRecord;
import com.czx.yimem.service.LogRecordService;
import com.czx.yimem.service.admin.MembserService;
import com.czx.yimem.utils.IpUtil;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.security.Principal;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 * @Package:  系统日志：切面处理类 切面只记录除登陆和退出的记录 登录退出日志在监听器中记录
 * @Version: 1.0
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private LogRecordService logRecordService;
    @Autowired
    private MembserService membserService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.czx.yimem.Annotation.SysLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {

        //保存日志
        LogRecord sysLog = new LogRecord();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        SysLog myLog = method.getAnnotation(SysLog.class);
        if (null!=myLog) {
            String value = myLog.value();
            sysLog.setRemark(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
        //获取请求
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        //获取用户名
        Principal principal = request.getUserPrincipal();
        sysLog.setName(membserService.getName(principal.getName()));
        sysLog.setAccount(principal.getName());
        //获取用户ip地址
        sysLog.setIp(IpUtil.getIpAddr(request));
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);
        //调用service保存SysLog实体类到数据库
        logRecordService.insertSelective(sysLog);
    }

}
