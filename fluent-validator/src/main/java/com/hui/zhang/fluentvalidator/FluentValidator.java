package com.hui.zhang.fluentvalidator;

import com.hui.zhang.fluentvalidator.annotation.NotThreadSafe;
import com.hui.zhang.fluentvalidator.annotation.Stateful;
import com.hui.zhang.fluentvalidator.util.Preconditions;
import com.hui.zhang.fluentvalidator.validator.element.ValidatorElement;
import com.hui.zhang.fluentvalidator.validator.element.ValidatorElementList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * @author zhanghui32
 * @date 2017/3/15
 * 链式调用验证器
 */
@NotThreadSafe
@Stateful
public class FluentValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FluentValidator.class);
    /**
     * 验证器链，惰性求值期间就是不断的改变这个链表，及时求值期间就是遍历链表依次执行验证
     */
    private ValidatorElementList validatorElementList = new ValidatorElementList();

    /**
     * 是否一旦发生验证错误就退出，默认为true
     *
     * @see #failFast()
     * @see #failOver()
     */
    private boolean isFailFast = true;

    /**
     * 验证器上下文
     * <p/>
     * 该<tt>context</tt>可以在所有验证器间共享数据
     */
    private ValidatorContext context = new ValidatorContext();

    /**
     * 验证结果，仅内部使用，外部使用验证结果需要使用{@link #result(ResultCollector)}来做收殓处理
     */
    private ValidationResult result = new ValidationResult();

    /**
     * 默认验证回调
     */
    protected ValidateCallback defaultCb = new DefaultValidateCallback();
    /**
     * 记录上一次添加的验证器数量，用于{@link #when(boolean)}做判断条件是否做当前验证
     */
    private int lastAddCount = 0;

    /**
     * 按照默认验证回调条件，开始使用验证
     * 采用默认回调函数进行处理
     *
     * @return FluentValidator
     */
    public FluentValidator doValidate() {
        return doValidate(defaultCb);
    }

    /**
     * 转换为对外的验证结果，在<code>FluentValidator.on(..).on(..).doValidate()</code>这一连串“<a href="https://en.wikipedia
     * .org/wiki/Lazy_evaluation">惰性求值</a>”计算后的“及时求值”收殓出口。
     * <p/>
     * &lt;T&gt;是验证结果的泛型
     *
     * @param resultCollector 验证结果收集器
     * @return 对外验证结果
     */
    public <T> T result(ResultCollector<T> resultCollector) {
        return resultCollector.toResult(result);
    }

    /**
     * 将键值对放入上下文
     * 此方法可以设置访问，校验之外所需对象信息
     *
     * @param key   键
     * @param value 值
     * @return FluentValidator
     */
    public FluentValidator putAttribute2Context(String key, Object value) {
        if (context == null) {
            context = new ValidatorContext();
        }
        context.setAttribute(key, value);
        return this;
    }

    /**
     * 按照指定验证回调条件，开始使用验证
     *
     * @param cb 验证回调
     * @return FluentValidator
     * @see ValidateCallback
     */
    public FluentValidator doValidate(ValidateCallback cb) {
        Preconditions.checkNotNull(cb, "ValidateCallback should not be NULL");
        if (validatorElementList.isEmpty()) {
            LOGGER.debug("Nothing to validate");
            return this;
        }
        context.setResult(result);

        LOGGER.debug("Start to validate through " + validatorElementList);
        long start = System.currentTimeMillis();
        try {
            for (ValidatorElement element : validatorElementList.getAllValidatorElements()) {
                Object target = element.getTarget();
                Validator v = element.getValidator();
                try {
                    if (!v.validate(context, target)) {
                        result.setIsSuccess(false);
                        if (isFailFast) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    try {
                        cb.onUncaughtException(v, e, target);
                    } catch (Exception e1) {
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.error(v + " onException or onUncaughtException throws exception due to " + e1
                                    .getMessage(), e1);
                        }
                        throw new RuntimeException(e1);
                    }
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.error(v + " failed due to " + e.getMessage(), e);
                    }
                    throw new RuntimeException(e);
                }
            }

            if (result.isSuccess()) {
                cb.onSuccess(validatorElementList);
            } else {
                cb.onFail(validatorElementList, result.getErrors());
            }
        } finally {
            int timeElapsed = (int) (System.currentTimeMillis() - start);
            LOGGER.debug("End to validate through" + validatorElementList + " costing " + timeElapsed + "ms with "
                    + "isSuccess=" + result.isSuccess());
            result.setTimeElapsed(timeElapsed);
        }
        return this;
    }

    /**
     * 出错即退出
     *
     * @return FluentValidator
     */
    public FluentValidator failFast() {
        this.isFailFast = true;
        return this;
    }

    /**
     * 出错不退出而继续
     *
     * @return FluentValidator
     */
    public FluentValidator failOver() {
        this.isFailFast = false;
        return this;
    }

    /**
     * 获取验证器对象
     *
     * @return FluentValidator
     */
    public static FluentValidator checkAll() {
        return new FluentValidator();
    }

    /**
     * 将验证对象及验证器加入验证链表
     *
     * @param t 待验证对象
     * @param v 验证器
     * @return FluentValidator
     */
    public <T> FluentValidator on(T t, Validator<T> v) {
        Preconditions.checkNotNull(v, "Validator should not be NULL");
        doAdd(new ValidatorElement(t, v));
        lastAddCount = 1;
        return this;
    }

    /**
     * 在待验证对象<tt>t</tt>上，使用<tt>chain</tt>验证器链进行验证
     *
     * @param t     待验证对象
     * @param chain 验证器链
     * @return FluentValidator
     */
    public <T> FluentValidator on(T t, ValidatorChain chain) {
        Preconditions.checkNotNull(chain, "ValidatorChain should not be NULL");
        if (CollectionUtils.isEmpty(chain.getValidators())) {
            lastAddCount = 0;
        } else {
            for (Validator v : chain.getValidators()) {
                doAdd(new ValidatorElement(t, v));
            }
            lastAddCount = chain.getValidators().size();
        }

        return this;
    }

    /**
     * 当不满足expression条件时
     * 移除最后一个验证操作
     *
     * @param expression 满足条件表达式
     * @return FluentValidator
     */
    public FluentValidator when(boolean expression) {
        if (!expression) {
            for (int i = 0; i < lastAddCount; i++) {
                validatorElementList.getList().removeLast();
            }
        }
        return this;
    }

    /**
     * 将验证对象及其验证器放入{@link #validatorElementList}中
     *
     * @param element 验证对象及其验证器封装类
     */
    protected void doAdd(ValidatorElement element) {
        validatorElementList.add(element);
        LOGGER.debug(element + " will be performed");
    }
}
