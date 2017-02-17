package com.hui.zhang.jingbean.profiler;

/**
 * @author zhanghui32
 * @date 2017/2/17
 * 模拟监控
 */
public class Profiler {

    public static CallerInfo registerInfo(String key, String appName, boolean enableHeartbeat, boolean enableTP) {
        return new CallerInfo();
    }

    public static void registerInfoEnd(CallerInfo callerInfo) {

    }

    public static void functionError(CallerInfo callerInfo) {

    }

    public static void businessAlarm(String key, long time, String detail) {

    }
}
