package com.hui.zhang.fluentvalidator;

import com.hui.zhang.fluentvalidator.validator.element.ValidatorElementList;

import java.util.List;

/**
 * @author zhanghui32
 * @date 2017/3/15
 * 默认回调函数
 */
public class DefaultValidateCallback implements ValidateCallback {
    public void onSuccess(ValidatorElementList validatorElementList) {

    }

    public void onFail(ValidatorElementList validatorElementList, List<ValidationError> errors) {

    }

    public void onUncaughtException(Validator validator, Exception e, Object target) throws Exception {

    }
}
