package com.czx.yimem.entity;

public class QsCategoryMajor {
    private Integer id;

    private Integer parentid;

    private String categoryname;

    private Integer categoryOrder;

    public QsCategoryMajor(Integer id, Integer parentid, String categoryname, Integer categoryOrder) {
        this.id = id;
        this.parentid = parentid;
        this.categoryname = categoryname;
        this.categoryOrder = categoryOrder;
    }

    public QsCategoryMajor() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }
}