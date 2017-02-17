package com.hui.zhang.jingbean.impl;

import com.hui.zhang.jingbean.JingBeanCallback;
import com.hui.zhang.jingbean.JingBeanService;
import com.hui.zhang.jingbean.domain.JingBeanGroupEnum;
import com.hui.zhang.jingbean.domain.JingBeanResponse;
import com.hui.zhang.jingbean.rpc.Result;
import com.hui.zhang.jingbean.JingBeanRoute;
import com.hui.zhang.jingbean.domain.JingBeanMethodEnum;
import com.hui.zhang.jingbean.domain.JingBeanRequest;
import com.hui.zhang.jingbean.profiler.CallerInfo;
import com.hui.zhang.jingbean.profiler.Profiler;
import com.hui.zhang.jingbean.rpc.JPeasRequest;
import com.hui.zhang.jingbean.rpc.JingBeanJsfFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 支付服务
 */
public class JingBeanServiceImpl implements JingBeanService, InitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(JingBeanServiceImpl.class);
    @Autowired
    private JingBeanRoute jpeasDependencyRoute;
    /**
     * 监控appName
     */
    private String umpAppName;
    /**
     * 方法监控前缀
     */
    private String umpKeyPrefix;

    /**
     * 监控key
     */
    private String umpKey;

    /**
     * 消费
     *
     * @param jingBeanRequest
     * @param jsfAlias
     * @param jingBeanCallback
     * @return
     */
    public JingBeanResponse consumeBeans(JingBeanRequest jingBeanRequest, JingBeanGroupEnum jsfAlias, JingBeanCallback jingBeanCallback) {
        CallerInfo info = Profiler.registerInfo(umpKey + ".consumeBeans", umpAppName, false, true);
        try {
            JingBeanJsfFacade jingBeanJsfFacade = jpeasDependencyRoute.dynamicFacadeGroup(jsfAlias);
            JPeasRequest jPeasRequest = getJPeasRequest(jingBeanRequest, JingBeanMethodEnum.CONSUMEBEANS);
            Result<String> result = jingBeanJsfFacade.consumeBeans(jPeasRequest);
            return jingBeanCallback.doResultCallback(result, JingBeanMethodEnum.CONSUMEBEANS.getValue(), umpAppName, jingBeanRequest.getSn());
        } catch (Exception e) {
            Profiler.functionError(info);
            LOGGER.error("consumeBeans error! sn:{}, exception:", jingBeanRequest.getSn(), e);
            return getExceptionResponse(false, jingBeanRequest.getSn(), e.getMessage());
        } finally {
            Profiler.registerInfoEnd(info);
        }
    }

    /**
     * 查询余额
     *
     * @param jingBeanRequest
     * @param jsfAlias
     * @param jingBeanCallback
     * @return
     */
    public JingBeanResponse balanceBeans(JingBeanRequest jingBeanRequest, JingBeanGroupEnum jsfAlias, JingBeanCallback jingBeanCallback) {
        CallerInfo info = Profiler.registerInfo(umpKey + ".balanceBeans", umpAppName, false, true);
        try {
            JingBeanJsfFacade jingBeanJsfFacade = jpeasDependencyRoute.dynamicFacadeGroup(jsfAlias);
            JPeasRequest jPeasRequest = getJPeasRequest(jingBeanRequest, JingBeanMethodEnum.BALANCEBEANS);
            Result<Integer> result = jingBeanJsfFacade.balanceBeans(jPeasRequest);
            return jingBeanCallback.doResultCallback(result, JingBeanMethodEnum.BALANCEBEANS.getValue(), umpAppName, jingBeanRequest.getSn());
        } catch (Exception e) {
            Profiler.functionError(info);
            LOGGER.error("balanceBeans error! sn:{}, exception:", jingBeanRequest.getSn(), e);
            return getExceptionResponse(false, jingBeanRequest.getSn(), e.getMessage());
        } finally {
            Profiler.registerInfoEnd(info);
        }
    }

    /**
     * 发豆
     *
     * @param jingBeanRequest
     * @param jsfAlias
     * @param jingBeanCallback
     * @return
     */
    public JingBeanResponse incomeBeans(JingBeanRequest jingBeanRequest, JingBeanGroupEnum jsfAlias, JingBeanCallback jingBeanCallback) {
        CallerInfo info = Profiler.registerInfo(umpKey + ".incomeBeans", umpAppName, false, true);
        try {
            JingBeanJsfFacade jingBeanJsfFacade = jpeasDependencyRoute.dynamicFacadeGroup(jsfAlias);
            JPeasRequest jPeasRequest = getJPeasRequest(jingBeanRequest, JingBeanMethodEnum.INCOMEBEANS);
            Result<String> result = jingBeanJsfFacade.incomeBeans(jPeasRequest);
            return jingBeanCallback.doResultCallback(result, JingBeanMethodEnum.INCOMEBEANS.getValue(), umpAppName, jingBeanRequest.getSn());
        } catch (Exception e) {
            Profiler.functionError(info);
            LOGGER.error("incomeBeans error! sn:{}, exception:", jingBeanRequest.getSn(), e);
            return getExceptionResponse(false, jingBeanRequest.getSn(), e.getMessage());
        } finally {
            Profiler.registerInfoEnd(info);
        }
    }

    /**
     * 获取异常返回信息
     *
     * @param result  返回结果
     * @param sn      交易流水号
     * @param message 异常信息
     * @return
     */
    private JingBeanResponse getExceptionResponse(Object result, String sn, String message) {
        JingBeanResponse response = new JingBeanResponse();
        response.setCode(sn);
        response.setMsg(message);
        response.setResult(result);
        return response;
    }

    /**
     * 获取京豆支付 请求参数
     *
     * @param jingBeanRequest
     * @param methodEnum
     * @return
     */
    private JPeasRequest getJPeasRequest(JingBeanRequest jingBeanRequest, JingBeanMethodEnum methodEnum) {
        JPeasRequest jPeasRequest = new JPeasRequest();
        jPeasRequest.setKey(jingBeanRequest.getKey());
        jPeasRequest.setCallerId(jingBeanRequest.getCallerId());
        jPeasRequest.setBusinessId(jingBeanRequest.getBusinessId());
        jPeasRequest.setPin(jingBeanRequest.getPin());
        jPeasRequest.setSignature(getSignature(jPeasRequest.getCallerId(), jPeasRequest.getBusinessId(), jPeasRequest.getPin(), jPeasRequest.getKey()));
        jPeasRequest.setDomain(jingBeanRequest.getDomain());
        /**
         * 当方法是查询用户账户京豆余额时，直接返回以上请求参数
         */
        if (methodEnum.equals(JingBeanMethodEnum.BALANCEBEANS)) {
            return jPeasRequest;
        }
        jPeasRequest.setAmount(jingBeanRequest.getAmount());
        jPeasRequest.setOrgId(jingBeanRequest.getOrgId());
        jPeasRequest.setTopBusinessId(jingBeanRequest.getTopBusinessId());
        jPeasRequest.setSecondBusinessId(jingBeanRequest.getSecondBusinessId());
        jPeasRequest.setSn(jingBeanRequest.getSn());
        jPeasRequest.setUserVisible(jingBeanRequest.getUserVisible());
        jPeasRequest.setMemo(jingBeanRequest.getMemo());
        return jPeasRequest;

    }

    /**
     * 获取客户端签名
     *
     * @param callerId
     * @param businessId
     * @param pin
     * @param key
     * @return
     */
    private String getSignature(String callerId, String businessId, String pin, String key) {
        StringBuilder builder = new StringBuilder();
        builder.append(callerId)
                .append("_")
                .append(businessId)
                .append("_")
                .append(pin)
                .append("_")
                .append(key);
        return builder.toString();
    }

    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isEmpty(umpAppName)) {
            throw new RuntimeException("the umpAppName is not null !");
        }

        if (StringUtils.isEmpty(umpKeyPrefix)) {
            throw new RuntimeException("the umpKeyPrefix is not null !");
        }
        umpKey = umpKeyPrefix + "." + JingBeanServiceImpl.class.getSimpleName();
    }

    public String getUmpKeyPrefix() {
        return umpKeyPrefix;
    }

    public void setUmpKeyPrefix(String umpKeyPrefix) {
        this.umpKeyPrefix = umpKeyPrefix;
    }

    public String getUmpAppName() {
        return umpAppName;
    }

    public void setUmpAppName(String umpAppName) {
        this.umpAppName = umpAppName;
    }
}
