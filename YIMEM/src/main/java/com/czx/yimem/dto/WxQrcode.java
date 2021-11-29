package com.czx.yimem.dto;

import java.io.Serializable;
import java.util.Date;

public class WxQrcode implements Serializable {
    private Integer id;
    private int type;//二维码类型 0：临时   1：永久
    //	private int expire_seconds = -1;//-1表示永久
    //	private String action_name;
    //	private int scene_id;//场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）   （临时可用/永久可用）
    //	private String scene_str;//场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段    (永久可用)
	//===============返回参数================
	private String ticket;//返回的ticket 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
	private String url;//二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	private int concernnum;//关注量
    private String keyname;
    private String scenename;
    private int keywords;
    private String actionname;
    private String expireseconds;
    private int sceneid;
    private String scenestr;
    private String codeurl;
    private Date ctime;
    private Integer scannum;
	public int getConcernnum() {
		return concernnum;
	}
	public void setConcernnum(int concernnum) {
		this.concernnum = concernnum;
	}
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public void setSceneid(int sceneid) {
		this.sceneid = sceneid;
	}
    public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getScenename() {
        return scenename;
    }
    public void setScenename(String scenename) {
        this.scenename = scenename == null ? null : scenename.trim();
    }
    public int getKeywords() {
        return keywords;
    }
    public String getActionname() {
        return actionname;
    }
    public void setKeywords(int keywords) {
		this.keywords = keywords;
	}
    public void setActionname(String actionname) {
        this.actionname = actionname == null ? null : actionname.trim();
    }
    public String getExpireseconds() {
        return expireseconds;
    }
    public void setExpireseconds(String expireseconds) {
        this.expireseconds = expireseconds == null ? null : expireseconds.trim();
    }
    public int getSceneid() {
        return sceneid;
    }
    public String getScenestr() {
        return scenestr;
    }
    public void setScenestr(String scenestr) {
        this.scenestr = scenestr == null ? null : scenestr.trim();
    }
    public String getCodeurl() {
        return codeurl;
    }
    public void setCodeurl(String codeurl) {
        this.codeurl = codeurl == null ? null : codeurl.trim();
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    public Date getCtime() {
        return ctime;
    }
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    public Integer getScannum() {
        return scannum;
    }
    public void setScannum(Integer scannum) {
        this.scannum = scannum;
    }


    /**
     * @author yurh
     * @description  临时
     * @return
     * @update 2016年10月9日 上午11:02:45
     */
    public String toTempJson(){
    	String json = "{\"expire_seconds\": "+this.getExpireseconds()+", \"action_name\": "+this.getActionname()+", \"action_info\": {\"scene\": {\"scene_id\": "+this.getSceneid()+"}}}";
    	return json;
    }

    /**
     * @author yurh
     * @description  永久
     * @return
     * @update 2016年10月9日 上午11:02:56
     */
    public String toPerJson(){
    	String json = "{\"action_name\": "+this.getActionname()+", \"action_info\": {\"scene\": {\"scene_str\": "+this.getScenestr()+"}}}";
    	return json;
    }


}
