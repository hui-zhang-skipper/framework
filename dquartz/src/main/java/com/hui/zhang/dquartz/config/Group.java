package com.hui.zhang.dquartz.config;

import java.util.List;

/**
 * @author zhanghui32
 * @date 2017/2/21
 * ip分组
 */
public class Group {
    private String name;
    private List<String> ids;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
