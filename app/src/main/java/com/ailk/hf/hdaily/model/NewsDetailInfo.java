package com.ailk.hf.hdaily.model;

import java.util.List;

/**
 * Created by huangfu on 2016/12/30 15.:21
 */
public class NewsDetailInfo {
    private String title; //: "中国古代家具发展到今天有两个高峰，一个两宋一个明末（多图）",
    private String ga_prefix; //: "052321",
    private List<String> images; //: ["http://p1.zhimg.com/45/b9/45b9f057fc1957ed2c946814342c0f02.jpg"],
    private String image; //: ["http://p1.zhimg.com/45/b9/45b9f057fc1957ed2c946814342c0f02.jpg"],
    private String type; //: 0,
    private String id; //: 3930445
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
