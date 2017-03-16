package com.hui.zhang.fluentvalidator;

import com.hui.zhang.fluentvalidator.util.CollectionUtil;
import com.hui.zhang.fluentvalidator.util.Function;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public class ResultCollectors {
    /**
     * 框架提供的一个简单结果收集器
     */
    static class SimpleResultCollectorImpl implements ResultCollector<Result> {

        public Result toResult(ValidationResult result) {
            Result ret = new Result();
            if (result.isSuccess()) {
                ret.setIsSuccess(true);
            } else {
                ret.setIsSuccess(false);
                ret.setErrors(CollectionUtil.transform(result.getErrors(), new Function<ValidationError, String>() {

                    public String apply(ValidationError input) {
                        return input.getErrorMsg();
                    }
                }));
            }

            return ret;
        }
    }
    /**
     * 静态方法返回一个简单结果收集器
     *
     * @return 简单的结果收集器<code>ResultCollectorImpl</code>
     */
    public static ResultCollector<Result> simpleCollector() {
        return new SimpleResultCollectorImpl();
    }
}
