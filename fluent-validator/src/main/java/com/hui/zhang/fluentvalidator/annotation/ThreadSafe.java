package com.hui.zhang.fluentvalidator.annotation;

import java.lang.annotation.*;

/**
 * 标记为线程安全的注解，标示类或者方法必须是线程安全实现的
 *
 * @author zhangxu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ThreadSafe {
}
