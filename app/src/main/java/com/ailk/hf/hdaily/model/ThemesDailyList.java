package com.ailk.hf.hdaily.model;

import java.util.List;

/**
 * Created by huangfu on 2017/1/3 14.:45
 */
public class ThemesDailyList {
    private String limit;
    private List<ThemesDaily> subscribed;//已订阅条目
    private List<ThemesDaily> others;//其他条目

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public List<ThemesDaily> getOthers() {
        return others;
    }

    public void setOthers(List<ThemesDaily> others) {
        this.others = others;
    }

    public List<ThemesDaily> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<ThemesDaily> subscribed) {
        this.subscribed = subscribed;
    }
}
