package com.czx.yimem.Annotation;

import java.lang.annotation.*;

/**
 * 定义系统日志注解
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
