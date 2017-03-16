package com.hui.zhang.fluentvalidator.validator.element;

import com.hui.zhang.fluentvalidator.Validator;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public class ValidatorElement {
    /**
     * 验证器
     */
    private Validator validator;

    /**
     * 待验证对象
     */
    private Object target;


    /**
     * create
     *
     * @param target    待验证对象
     * @param validator 验证器
     */
    public ValidatorElement(Object target, Validator validator) {
        this.target = target;
        this.validator = validator;
    }


    public Object getTarget() {
        return target;
    }

    public Validator getValidator() {
        return validator;
    }
}
