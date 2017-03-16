package com.hui.zhang.fluentvalidator;

import com.hui.zhang.fluentvalidator.util.CollectionUtil;

import java.util.Map;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public class ValidatorContext {
    /**
     * 验证器均可以共享使用的属性键值对
     */
    private Map<String, Object> attributes;


    /**
     * 调用结果对象
     */
    public ValidationResult result;

    /**
     * 添加错误信息
     *
     * @param msg 错误信息
     */
    public void addErrorMsg(String msg) {
        result.addError(ValidationError.create(msg));
    }


    /**
     * 添加错误信息
     *
     * @param validationError 验证错误
     */
    public void addError(ValidationError validationError) {
        result.addError(validationError);
    }

    /**
     * 获取属性
     *
     * @param key 键
     * @return 值
     */
    public Object getAttribute(String key) {
        if (attributes != null && !attributes.isEmpty()) {
            return attributes.get(key);
        }
        return null;
    }

    /**
     * 根据类型<t>T</t>直接获取属性值
     *
     * @param key   键
     * @param clazz 值类型
     * @return 值
     */
    public <T> T getAttribute(String key, Class<T> clazz) {
        return (T) getAttribute(key);
    }

    public void setAttribute(String key, Object value) {
        if (attributes == null) {
            attributes = CollectionUtil.createHashMap();
        }
        attributes.put(key, value);
    }


    /**
     * 设置验证结果
     *
     * @param result 验证结果
     */
    public void setResult(ValidationResult result) {
        this.result = result;
    }
}
