package com.hui.zhang.fluentvalidator;

import java.util.List;

/**
 * 多个组成的调用链
 */
public class ValidatorChain {

    /**
     * 验证器list
     */
    private List<Validator> validators;

    /**
     * get validators
     *
     * @return validators
     */
    public List<Validator> getValidators() {
        return validators;
    }

    /**
     * set validators
     *
     * @param validators
     */
    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }

    @Override
    public String toString() {
        return "ValidatorChain{" +
                "validators=" + validators +
                '}';
    }
}
