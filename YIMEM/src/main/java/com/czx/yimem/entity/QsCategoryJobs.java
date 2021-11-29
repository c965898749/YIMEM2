package com.czx.yimem.entity;

import java.math.BigDecimal;
import java.util.List;

public class QsCategoryJobs {
    private Integer id;

    private Short parentid;

    private String categoryname;

    private Short categoryOrder;

    private String statJobs;

    private String statResume;

    private String spell;

    private String relation1;

    private String relation1Cn;

    private String relation2;

    private String relation2Cn;

    private String content;

    private  BigDecimal money;

    private BigDecimal brokerage;

    public QsCategoryJobs(Integer id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    public QsCategoryJobs(Integer id, Short parentid, String categoryname, Short categoryOrder, String statJobs, String statResume, String spell, String relation1, String relation1Cn, String relation2, String relation2Cn, String content) {
        this.id = id;
        this.parentid = parentid;
        this.categoryname = categoryname;
        this.categoryOrder = categoryOrder;
        this.statJobs = statJobs;
        this.statResume = statResume;
        this.spell = spell;
        this.relation1 = relation1;
        this.relation1Cn = relation1Cn;
        this.relation2 = relation2;
        this.relation2Cn = relation2Cn;
        this.content = content;
    }

    public QsCategoryJobs(Integer id, Short parentid, String categoryname, Short categoryOrder, String statJobs, String statResume, String spell, String relation1, String relation1Cn, String relation2, String relation2Cn) {
        this.id = id;
        this.parentid = parentid;
        this.categoryname = categoryname;
        this.categoryOrder = categoryOrder;
        this.statJobs = statJobs;
        this.statResume = statResume;
        this.spell = spell;
        this.relation1 = relation1;
        this.relation1Cn = relation1Cn;
        this.relation2 = relation2;
        this.relation2Cn = relation2Cn;
    }

    public QsCategoryJobs() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getParentid() {
        return parentid;
    }

    public void setParentid(Short parentid) {
        this.parentid = parentid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    public Short getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Short categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public String getStatJobs() {
        return statJobs;
    }

    public void setStatJobs(String statJobs) {
        this.statJobs = statJobs == null ? null : statJobs.trim();
    }

    public String getStatResume() {
        return statResume;
    }

    public void setStatResume(String statResume) {
        this.statResume = statResume == null ? null : statResume.trim();
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell == null ? null : spell.trim();
    }

    public String getRelation1() {
        return relation1;
    }

    public void setRelation1(String relation1) {
        this.relation1 = relation1 == null ? null : relation1.trim();
    }

    public String getRelation1Cn() {
        return relation1Cn;
    }

    public void setRelation1Cn(String relation1Cn) {
        this.relation1Cn = relation1Cn == null ? null : relation1Cn.trim();
    }

    public String getRelation2() {
        return relation2;
    }

    public void setRelation2(String relation2) {
        this.relation2 = relation2 == null ? null : relation2.trim();
    }

    public String getRelation2Cn() {
        return relation2Cn;
    }

    public void setRelation2Cn(String relation2Cn) {
        this.relation2Cn = relation2Cn == null ? null : relation2Cn.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
