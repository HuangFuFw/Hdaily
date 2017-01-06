package com.ailk.hf.hdaily.model;

import java.util.List;

/**
 * Created by huangfu on 2017/1/3 15.:23
 */
public class ThemesDailyInfo {
    private List<NewsInfo> stories;// 该主题日报中的文章列表
    private String description;//    description : 该主题日报的介绍
    private String background;//background : 该主题日报的背景图片（大图）
    private String color;// 颜色，作用未知
    private String name;//该主题日报的名称
    private String image; //背景图片的小图版本
    private List<DailyEditorsInfo> editors;// : 该主题日报的编辑（『用户推荐日报』中此项的指是一个空数组，在 App 中的主编栏显示为『许多人』，点击后访问该主题日报的介绍页面，请留意）
    private String image_source; //图像的版权信息

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

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

    public List<DailyEditorsInfo> getEditors() {
        return editors;
    }

    public void setEditors(List<DailyEditorsInfo> editors) {
        this.editors = editors;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewsInfo> getStories() {
        return stories;
    }

    public void setStories(List<NewsInfo> stories) {
        this.stories = stories;
    }
}
