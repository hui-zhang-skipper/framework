package com.hui.zhang.fluentvalidator.annotation;

import java.lang.annotation.*;

/**
 * 标记为有状态的，反义词为无状态的stateless
 *
 * @author zhangxu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Stateful {
}
