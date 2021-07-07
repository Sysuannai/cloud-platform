package com.yzchn.platform.common.annotation;

import java.lang.annotation.*;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName OperLog.java
 * @Description TODO 操作日志注解
 * @createTime 2021年06月25日 09:53:00
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface OperLog {
    String operModul() default ""; // 操作模块

    String operType() default "";  // 操作类型

    String operDesc() default "";  // 操作说明
}
