package com.czx.yimem.entity;

public class Gift {
    private Integer id;

    private String name;

    private String img;

    private Integer point;

    private Integer state;

    public Gift(Integer id, String name, String img, Integer point, Integer state) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.point = point;
        this.state = state;
    }

    public Gift() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}