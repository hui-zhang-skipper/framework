package com.hui.zhang.fluentvalidator;

import com.hui.zhang.fluentvalidator.annotation.NotThreadSafe;
import com.hui.zhang.fluentvalidator.util.CollectionUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
@NotThreadSafe
public class ValidationResult {
    /**
     * 是否成功，一旦发生错误，即置为false，默认为{@value}
     */
    private boolean isSuccess = true;

    /**
     * 验证错误
     */
    private List<ValidationError> errors;

    /**
     * 验证总体耗时，指通过<code>FluentValidator.doValidate(..)</code>真正“及时求值”过程中的耗时
     */
    private int timeElapsed;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * 添加错误
     *
     * @param error 错误
     */
    public void addError(ValidationError error) {
        if (CollectionUtils.isEmpty(errors)) {
            errors = CollectionUtil.createArrayList();
        }
        errors.add(error);
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
