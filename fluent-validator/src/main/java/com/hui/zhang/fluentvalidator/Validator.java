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
    /**
     * 判断在该对象上是否接受或者需要验证
     * <p/>
     * 如果返回true验证，否则跳过该验证器
     *
     * @param context 验证上下文
     * @param t       待验证对象
     * @return 是否接受验证
     */
    boolean accept(ValidatorContext context, T t);
}
