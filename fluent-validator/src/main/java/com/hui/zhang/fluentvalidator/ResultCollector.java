package com.hui.zhang.fluentvalidator;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public interface ResultCollector<T> {
    /**
     * 转换为对外结果
     *
     * @param result 框架内部验证结果
     *
     * @return 对外验证结果对象
     */
    T toResult(ValidationResult result);
}
