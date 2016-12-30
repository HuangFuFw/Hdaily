package com.ailk.hf.hdaily.model;

import java.util.List;

/**
 * Created by huangfu on 2016/12/30 15.:17
 */
public class LatestNews {
    private String date;
    private List<NewsDetailInfo> stories;
    private List<NewsDetailInfo> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<NewsDetailInfo> getStories() {
        return stories;
    }

    public void setStories(List<NewsDetailInfo> stories) {
        this.stories = stories;
    }

    public List<NewsDetailInfo> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<NewsDetailInfo> top_stories) {
        this.top_stories = top_stories;
    }
}
