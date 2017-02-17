package com.hui.zhang.jingbean.rpc;

/**
 * @author zhanghui32
 * @date 2017/2/17
 * 模拟rpc实现
 */
public class JingBeanJsfFacadeImpl implements JingBeanJsfFacade {

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    private String alias;

    public Result<String> incomeBeans(JPeasRequest request) {
        Result<String> result = new Result<String>();
        result.setCode("001");
        result.setMsg("success");
        result.setData("data");
        return result;
    }

    public Result<String> consumeBeans(JPeasRequest request) {
        Result<String> result = new Result<String>();
        result.setCode("002");
        result.setMsg("success");
        result.setData("data");
        return result;
    }

    public Result<Integer> balanceBeans(JPeasRequest request) {
        Result<Integer> result = new Result<Integer>();
        result.setCode("001");
        result.setMsg("success");
        result.setData(100);
        return result;
    }
}
