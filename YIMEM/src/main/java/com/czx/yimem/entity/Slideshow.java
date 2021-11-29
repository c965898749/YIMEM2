package com.czx.yimem.entity;

import java.util.Date;
import java.util.List;

public class Slideshow {
    private Integer ssId;

    private Integer id;

    private String toUser;

    private Date createTime;

    private String url;

    private String shortUrl;

    private List<LsImg> pic;

    public List<LsImg> getPic() {
        return pic;
    }

    public void setPic(List<LsImg> pic) {
        this.pic = pic;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    private String state;

    public Slideshow(Integer ssId, Integer id, String toUser, Date createTime, String url, String state) {
        this.ssId = ssId;
        this.id = id;
        this.toUser = toUser;
        this.createTime = createTime;
        this.url = url;
        this.state = state;
    }

    public Slideshow() {
        super();
    }

    public Integer getSsId() {
        return ssId;
    }

    public void setSsId(Integer ssId) {
        this.ssId = ssId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser == null ? null : toUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
