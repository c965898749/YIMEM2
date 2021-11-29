package com.czx.yimem.dto;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

//@ApiModel(value = "寄送地址参数")
public class Receiveaddress {

    private Integer id;
//    @ApiModelProperty(value = "用户id")
    private String memberid;
//    @ApiModelProperty(value = "收货人地址")
    private String linkman;
//    @ApiModelProperty(value = "收货人电话")
    private String linkphone;
//    @ApiModelProperty(value = "省")
    private String province;
//    @ApiModelProperty(value = "市")
    private String city;
//    @ApiModelProperty(value = "区")
    private String county;
//    @ApiModelProperty(value = "详细地址")
    private String address;
//    @ApiModelProperty(value = "是否默认地址")
    private Integer isdefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone == null ? null : linkphone.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }
}
