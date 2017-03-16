package com.hui.zhang.fluentvalidator.validator.element;

import com.hui.zhang.fluentvalidator.util.CollectionUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public class ValidatorElementList {

    /**
     * 待验证对象及其验证器链表
     */
    private LinkedList<ValidatorElement> validatorElementLinkedList = CollectionUtil.createLinkedList();

    /**
     * 加入待验证对象及其验证器
     *
     * @param element 待验证对象及其验证器封装类
     */
    public void add(ValidatorElement element) {
        this.validatorElementLinkedList.add(element);
    }

    /**
     * 获取待验证对象及其验证器链表长度
     *
     * @return 联调长度
     */
    public int size() {
        return validatorElementLinkedList.size();
    }

    /**
     * 获取验证器链
     *
     * @return 验证器链
     */
    public LinkedList<ValidatorElement> getList() {
        return validatorElementLinkedList;
    }

    /**
     * 验证器链是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return validatorElementLinkedList.isEmpty();
    }

    /**
     * 获取验证器链
     *
     * @return 验证器链
     */
    public List<ValidatorElement> getAllValidatorElements() {
        List<ValidatorElement> ret = new ArrayList<ValidatorElement>();
        for (ValidatorElement e : validatorElementLinkedList) {
            ret.add(e);
        }
        return ret;
    }

}
