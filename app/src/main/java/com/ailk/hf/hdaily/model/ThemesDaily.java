package com.ailk.hf.hdaily.model;

/**
 * Created by huangfu on 2017/1/3 14.:51
 */
public class ThemesDaily {
    private String color;//"color": 8307764,
    private String thumbnail;//"thumbnail": "http://pic4.zhimg.com/2c38a96e84b5cc8331a901920a87ea71.jpg",
    private String description;//"description": "内容由知乎用户推荐，海纳主题百万，趣味上天入地",
    private String id;//"id": 12,
    private String name;//"name": "用户推荐日报"

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
