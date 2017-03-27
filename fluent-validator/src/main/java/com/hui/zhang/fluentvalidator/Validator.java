package com.hui.zhang.fluentvalidator;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public interface Validator<T> {
    /**
     * 验证接口
     * @param context
     * @param t
     * @return
     */
    boolean validate(ValidatorContext context, T t);
}
