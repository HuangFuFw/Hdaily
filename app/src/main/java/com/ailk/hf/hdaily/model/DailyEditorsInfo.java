package com.ailk.hf.hdaily.model;

/**
 * Created by huangfu on 2017/1/3 15.:30
 */
public class DailyEditorsInfo {

    private String url; //: 主编的知乎用户主页
    private String bio; //: 主编的个人简介
    private String id; //: 数据库中的唯一表示符
    private String avatar; //: 主编的头像
    private String name; //: 主编的姓名
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
