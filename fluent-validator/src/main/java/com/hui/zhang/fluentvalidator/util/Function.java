package com.hui.zhang.fluentvalidator.util;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public interface Function<F,T> {
    /**
     * Returns the result of applying this function to {@code input}.
     */
    T apply(F input);
}
