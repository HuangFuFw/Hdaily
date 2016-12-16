package com.ailk.hf.hdaily.model;

import java.io.Serializable;

/**
 * Created by huangfu on 2016/12/2 15.:49
 */
public class SplashInfo implements Serializable{
    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
