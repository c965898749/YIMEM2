package com.czx.yimem.entity;

public class QsCategoryGroup {
    private Integer gId;

    private String gAlias;

    private String gName;

    private Boolean gSys;

    public QsCategoryGroup(Integer gId, String gAlias, String gName, Boolean gSys) {
        this.gId = gId;
        this.gAlias = gAlias;
        this.gName = gName;
        this.gSys = gSys;
    }

    public QsCategoryGroup() {
        super();
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getgAlias() {
        return gAlias;
    }

    public void setgAlias(String gAlias) {
        this.gAlias = gAlias == null ? null : gAlias.trim();
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName == null ? null : gName.trim();
    }

    public Boolean getgSys() {
        return gSys;
    }

    public void setgSys(Boolean gSys) {
        this.gSys = gSys;
    }
}