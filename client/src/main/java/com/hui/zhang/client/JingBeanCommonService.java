package com.hui.zhang.client;

import com.hui.zhang.jingbean.JingBeanService;
import com.hui.zhang.jingbean.domain.JingBeanGroupEnum;
import com.hui.zhang.jingbean.domain.JingBeanRequest;
import com.hui.zhang.jingbean.domain.JingBeanResponse;
import com.hui.zhang.jingbean.impl.DefaultJingBeanCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhanghui243
 * @date 2016-11-28
 * @description 京豆支付服务
 */
@Component
public class JingBeanCommonService {
    @Autowired
    private JingBeanService jingBeanService;
    @Autowired
    private JingBeanCommonRoute jingBeanCommonRoute;

    /**
     * 收入
     *
     * @param userPin 用户Pin
     * @param uuid    唯一交易码
     * @param amount  金额
     * @return 是否交易成功
     */
    public boolean incomeJingBeans(int gameId, String userPin, String uuid, int amount, String desc) {
        JingBeanRequest jingBeanRequest = getJingBeanRequest(gameId, userPin, uuid, amount, desc);
        JingBeanResponse<Boolean> response = jingBeanService.incomeBeans(jingBeanRequest, JingBeanGroupEnum.toEnum("LF_JPOS_CLOUD"), new DefaultJingBeanCallback());
        return response.getResult();
    }

    /**
     * 消费
     *
     * @param userPin 用户Pin
     * @param uuid    唯一交易码
     * @param amount  金额
     * @return 是否交易成功
     */
    public String consumeJingBeans(int gameId, String userPin, String uuid, int amount, String desc) {
        JingBeanRequest jingBeanRequest = getJingBeanRequest(gameId, userPin, uuid, amount, desc);
        JingBeanResponse<Boolean> response = jingBeanService.consumeBeans(jingBeanRequest, JingBeanGroupEnum.toEnum("LF_JPOS_CLOUD"), new DefaultJingBeanCallback());
        return response.getCode();
    }

    /**
     * 重新组装请求信息
     *
     * @param gameId  游戏id
     * @param userPin 用户pin
     * @param uuid    交易流水
     * @param amount  金额
     * @param desc    描述
     * @return
     */
    private JingBeanRequest getJingBeanRequest(int gameId, String userPin, String uuid, int amount, String desc) {
        JingBeanRequest jingBeanRequest = jingBeanCommonRoute.selectJingBeanPool(gameId);
        jingBeanRequest.setPin(userPin);
        jingBeanRequest.setAmount(amount);
        jingBeanRequest.setSn(uuid);
        jingBeanRequest.setMemo(desc);
        return jingBeanRequest;
    }

    /**
     * 重新组装请求信息 针对金额查询
     *
     * @param gameId  游戏id
     * @param userPin 用户pin
     * @return
     */
    private JingBeanRequest getJingBeanRequest(int gameId, String userPin) {
        JingBeanRequest jingBeanRequest = jingBeanCommonRoute.selectJingBeanPool(gameId);
        jingBeanRequest.setPin(userPin);
        return jingBeanRequest;
    }

    /**
     * 查询余额
     *
     * @param userPin 用户pin
     * @return 是否足够
     */
    public int balanceJingBeans(int gameId, String userPin) {
        JingBeanRequest jingBeanRequest = getJingBeanRequest(gameId, userPin);
        JingBeanResponse<Integer> response = jingBeanService.balanceBeans(jingBeanRequest, JingBeanGroupEnum.toEnum("LF_JPOS_CLOUD"), new DefaultJingBeanCallback());
        return response.getResult();
    }
}
