package com.czx.yimem.entity;

import java.util.Date;

public class LsImg {
    private Integer id;

    private String imageUrl;

    private String name;

    private String shortUrl;

    private String realPath;

    private Date createTime;

    public LsImg(Integer id, String imageUrl, String name, String shortUrl, String realPath, Date createTime) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.shortUrl = shortUrl;
        this.realPath = realPath;
        this.createTime = createTime;
    }

    public LsImg() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl == null ? null : shortUrl.trim();
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath == null ? null : realPath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}