package com.czx.yimem.entity;

public class QsCategory {
    private Integer cId;

    private Integer cParentid;

    private String cAlias;

    private String cName;

    private Integer cOrder;

    private String cIndex;

    private String cNote;

    private String statJobs;

    private String statResume;

    public QsCategory(Integer cId, Integer cParentid, String cAlias, String cName, Integer cOrder, String cIndex, String cNote, String statJobs, String statResume) {
        this.cId = cId;
        this.cParentid = cParentid;
        this.cAlias = cAlias;
        this.cName = cName;
        this.cOrder = cOrder;
        this.cIndex = cIndex;
        this.cNote = cNote;
        this.statJobs = statJobs;
        this.statResume = statResume;
    }

    public QsCategory() {
        super();
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcParentid() {
        return cParentid;
    }

    public void setcParentid(Integer cParentid) {
        this.cParentid = cParentid;
    }

    public String getcAlias() {
        return cAlias;
    }

    public void setcAlias(String cAlias) {
        this.cAlias = cAlias == null ? null : cAlias.trim();
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public Integer getcOrder() {
        return cOrder;
    }

    public void setcOrder(Integer cOrder) {
        this.cOrder = cOrder;
    }

    public String getcIndex() {
        return cIndex;
    }

    public void setcIndex(String cIndex) {
        this.cIndex = cIndex == null ? null : cIndex.trim();
    }

    public String getcNote() {
        return cNote;
    }

    public void setcNote(String cNote) {
        this.cNote = cNote == null ? null : cNote.trim();
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
}