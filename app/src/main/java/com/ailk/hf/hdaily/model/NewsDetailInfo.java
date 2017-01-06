package com.ailk.hf.hdaily.model;

import java.util.List;

/**
 * Created by huangfu on 2017/1/3 10.:45
 */
public class NewsDetailInfo {

    private String body;//: "<div class="main-wrap content-wrap">...</div>",
    private String image_source;//: "Yestone.com 版权图片库",
    private String title;//: "深夜惊奇 · 朋友圈错觉",
    private String image;//: "http://pic3.zhimg.com/2d41a1d1ebf37fb699795e78db76b5c2.jpg",
    private String share_url;//: "http://daily.zhihu.com/story/4772126",
    private List<String> js;//: 供手机端的 WebView(UIWebView) 使用
    private String ga_prefix;// : 供 Google Analytics 使用
//    private List<String> recommenders;// : 这篇文章的推荐者
    private String type; //: 新闻的类型
    private String id; //: 新闻的 id
    private List<String> css;// : 供手机端的 WebView(UIWebView) 使用


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
