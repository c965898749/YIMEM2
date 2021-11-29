/**
 * 2015-4-27
 * WeChatConf.java
 * author:赵永飞
 */
package com.czx.yimem.dto;

import java.util.Date;

/**
 * @author zhaoyf
 *
 */
public class WeChatConf {
	private int id;
	private String appid;
	private String secret;
	private String token;
	private String accesstoken;
	private String encodingAESKey;
	private String name;
	private String wechatno;
	private int orgtype;
	private int orgid;
	private String subscribelog;
	private String subscribetitle;
	private String subscribedescription;
	private String linkurl;
	private int is_api;
	private int wx_type;
	private String keyword;
	private String mch_id;
	private String apisecret;
	private String certfilepath;
	private String certname;
	private String sharetitle;
	private String shareimg;
	private String defaultreplay;
	private String  mallname;
	private String jsdkticket;
	private String reply;
	private Date ctime;
	private String apiticket;


	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getApiticket() {
		return apiticket;
	}
	public void setApiticket(String apiticket) {
		this.apiticket = apiticket;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return appid
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * @param appid 要设置的 appid
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * @return secret
	 */
	public String getSecret() {
		return secret;
	}
	/**
	 * @param secret 要设置的 secret
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	/**
	 * @return token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token 要设置的 token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return accesstoken
	 */
	public String getAccesstoken() {
		return accesstoken;
	}
	/**
	 * @param accesstoken 要设置的 accesstoken
	 */
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	/**
	 * @return encodingAESKey
	 */
	public String getEncodingAESKey() {
		return encodingAESKey;
	}
	/**
	 * @param encodingAESKey 要设置的 encodingAESKey
	 */
	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return wechatno
	 */
	public String getWechatno() {
		return wechatno;
	}
	/**
	 * @param wechatno 要设置的 wechatno
	 */
	public void setWechatno(String wechatno) {
		this.wechatno = wechatno;
	}
	/**
	 * @return orgtype
	 */
	public int getOrgtype() {
		return orgtype;
	}
	/**
	 * @param orgtype 要设置的 orgtype
	 */
	public void setOrgtype(int orgtype) {
		this.orgtype = orgtype;
	}
	/**
	 * @return orgid
	 */
	public int getOrgid() {
		return orgid;
	}
	/**
	 * @param orgid 要设置的 orgid
	 */
	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}
	/**
	 * @return subscribelog
	 */
	public String getSubscribelog() {
		return subscribelog;
	}
	/**
	 * @param subscribelog 要设置的 subscribelog
	 */
	public void setSubscribelog(String subscribelog) {
		this.subscribelog = subscribelog;
	}
	/**
	 * @return subscribetitle
	 */
	public String getSubscribetitle() {
		return subscribetitle;
	}
	/**
	 * @param subscribetitle 要设置的 subscribetitle
	 */
	public void setSubscribetitle(String subscribetitle) {
		this.subscribetitle = subscribetitle;
	}
	/**
	 * @return subscribedescription
	 */
	public String getSubscribedescription() {
		return subscribedescription;
	}
	/**
	 * @param subscribedescription 要设置的 subscribedescription
	 */
	public void setSubscribedescription(String subscribedescription) {
		this.subscribedescription = subscribedescription;
	}
	/**
	 * @return linkurl
	 */
	public String getLinkurl() {
		return linkurl;
	}
	/**
	 * @param linkurl 要设置的 linkurl
	 */
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	/**
	 * @return is_api
	 */
	public int getIs_api() {
		return is_api;
	}
	/**
	 * @param is_api 要设置的 is_api
	 */
	public void setIs_api(int is_api) {
		this.is_api = is_api;
	}
	/**
	 * @return wx_type
	 */
	public int getWx_type() {
		return wx_type;
	}
	/**
	 * @param wx_type 要设置的 wx_type
	 */
	public void setWx_type(int wx_type) {
		this.wx_type = wx_type;
	}
	/**
	 * @return keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword 要设置的 keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getApisecret() {
		return apisecret;
	}
	public void setApisecret(String apisecret) {
		this.apisecret = apisecret;
	}
	public String getCertfilepath() {
		return certfilepath;
	}
	public void setCertfilepath(String certfilepath) {
		this.certfilepath = certfilepath;
	}
	public String getCertname() {
		return certname;
	}
	public void setCertname(String certname) {
		this.certname = certname;
	}
	public String getSharetitle() {
		return sharetitle;
	}
	public void setSharetitle(String sharetitle) {
		this.sharetitle = sharetitle;
	}
	public String getShareimg() {
		return shareimg;
	}
	public void setShareimg(String shareimg) {
		this.shareimg = shareimg;
	}
	public String getDefaultreplay() {
		return defaultreplay;
	}
	public void setDefaultreplay(String defaultreplay) {
		this.defaultreplay = defaultreplay;
	}
	public String getMallname() {
		return mallname;
	}
	public void setMallname(String mallname) {
		this.mallname = mallname;
	}
	public String getJsdkticket() {
		return jsdkticket;
	}
	public void setJsdkticket(String jsdkticket) {
		this.jsdkticket = jsdkticket;
	}


}
