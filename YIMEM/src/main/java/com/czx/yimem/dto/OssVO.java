package com.czx.yimem.dto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class OssVO {
    /**
     * 服务调用方标识，JSON串表达，对象字段见总表附录一，各接口统一
     */
    private JSONObject svcCaller;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    @JsonFormat(
            locale = "zh",
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd"
    )
    private String timestamp;
    /**
     * JSON表达的开通派发工单信息
     */
    //简历附件
    private JSONObject CvFile;
    //邀约面试
    private JSONObject vpninviteGet;
    private JSONObject vpninviteUpdate;
    //分销排行榜
    private JSONObject vpnbrokerageRankingList;
    //分销排行榜
    private JSONObject vpnInterviewRankingList;
    //分销排行榜
    private JSONObject vpnRecommendRankingList;
    //分销者收入
    private JSONObject vpnincomeDetailGet;
    //余额
    private JSONObject vpnWalletGet;
    //分销者明细
    private JSONObject vpnrecommendDetailGet;
    //分销者推荐
    private JSONObject vpnrecommendGetResp;

    //公司账单详细
    private JSONObject vpnCompanyBillDetailGet;
    //    提现
    private JSONObject vpnApplicationForWithdrawalGet;
    private JSONObject vpnApplicationForWithdrawalUpdate;
    private JSONObject vpnApplicationForWithdrawalAdd;
    //公司账单
    private JSONObject vpnCompanyBillGet;
    private JSONObject vpnCompanyBillUpdate;

    //总表
    private JSONObject vpnIncomeGet;

    //求职者
    private JSONObject vpnjobhunterGet;
    //分销者
    private JSONObject vpndistributionGet;
    private JSONObject vpndistributionAdd;
    private JSONObject vpndistributionUpdate;

    //4.1.	岗位
    private JSONObject vpnjobGet;
    private JSONObject vpnjobAdd;
    private JSONObject vpnjobUpdate;
    private JSONObject vpnjobDel;

    //4.16.	职位类别企佣收费
    private JSONObject vpnjobcategoryGet;
    private JSONObject vpnjobcategoryAdd;
    private JSONObject vpnjobcategoryUpdate;
    private JSONObject vpnjobcategoryDel;

    //职业亮点
    private JSONObject vpnjobLightenEnumGet;
    private JSONObject vpnjobLightenEnumAdd;
    private JSONObject vpnjobLightenEnumUpdate;
    private JSONObject vpnjobLightenEnumDel;

    private JSONObject vpnjobCustomLightenEnumGet;

    //用户
    private JSONObject vpnuserGet;
    private JSONObject vpnuserAdd;
    private JSONObject vpnuserUpdate;
    private JSONObject vpnuserDel;

    //企业
    private JSONObject vpncompanyGet;
    private JSONObject vpncompanyAdd;
    private JSONObject vpncompanyUpdate;
    private JSONObject vpncompanyDel;

    //求职意向
    private JSONObject vpnjobWantedGet;
    private JSONObject vpnjobWantedAdd;
    private JSONObject vpnjobWantedUpdate;
    private JSONObject vpnjobWantedDel;

    //学历经历
    private JSONObject vpneducationExpGet;
    private JSONObject vpneducationExpAdd;
    private JSONObject vpneducationExpUpdate;
    private JSONObject vpneducationExpDel;

    //工作经验
    private JSONObject vpnjobExpGet;
    private JSONObject vpnjobExpAdd;
    private JSONObject vpnjobExpUpdate;
    private JSONObject vpnjobExpDel;

    //语言
    private JSONObject vpnlanguageGet;
    private JSONObject vpnlanguageAdd;
    private JSONObject vpnlanguageUpdate;
    private JSONObject vpnlanguageDel;

    //项目经验
    private JSONObject vpnprojectExpGet;
    private JSONObject vpnprojectExpAdd;
    private JSONObject vpnprojectExpUpdate;
    private JSONObject vpnprojectExpDel;

    //证书
    private JSONObject vpncertificateGet;
    private JSONObject vpncertificateAdd;
    private JSONObject vpncertificateUpdate;
    private JSONObject vpncertificateDel;

    //消息
    private JSONObject vpnmessageGet;
    private JSONObject vpnmessageAdd;
    private JSONObject vpnmessageDel;
    private JSONObject vpnmessageUpdate;

    //通知
    private JSONObject vpnnewsGet;
    private JSONObject vpnnewsAdd;
    private JSONObject vpnnewsUpdate;
    private JSONObject vpnnewsDel;

    //收藏
    private JSONObject vpncollectGet;
    private JSONObject vpncollectAdd;
    private JSONObject vpncollectUpdate;
    private JSONObject vpncollectDel;

    //简历列表
    private JSONObject vpndeliverGet;
    private JSONObject vpndeliverAdd;
    private JSONObject vpndeliverUpdate;
    private JSONObject vpndeliverDel;

    //简历流程
    private JSONObject vpnDeliverDetailGet;
    private JSONObject vpnDeliverDetailAdd;
    private JSONObject vpnDeliverDetailUpdate;
    private JSONObject vpnDeliverDetailDel;


    //4.181.	通用枚举
    private JSONObject vpnqsCategoryGet;
    private JSONObject vpnqsCategoryAdd;
    private JSONObject vpnqsCategoryUpdate;
    private JSONObject vpnqsCategoryDel;

    //4.185.	分类
    private JSONObject vpnqsCategoryGroupGet;

    //4.186.	职业类型
    private JSONObject vpnqsCategoryJobsGet;
    private JSONObject vpnqsCategoryJobsAdd;
    private JSONObject vpnqsCategoryJobsDel;
    private JSONObject vpnqsCategoryJobsUpdate;

    //省
    private JSONObject vpnRmProvinceGet;
    //区县
    private JSONObject vpnRmCountyGet;
    //地市
    private JSONObject vpnRmCityGet;

    //专业
    private JSONObject vpnQsCategoryMajorGet;

    //轮播
    private JSONObject vpnslideshowGet;

    //导航
    private JSONObject vpnnavigationGet;
    private JSONObject vpnnavigationAdd;
    private JSONObject vpnnavigationUpdate;
    private JSONObject vpnnavigationDel;

    //新手指导
    private JSONObject vpnbeginnerGet;
    private JSONObject vpnbeginnerAdd;
    private JSONObject vpnbeginnerUpdate;
    private JSONObject vpnbeginnerDel;

    //客服
    private JSONObject vpncustomerGet;
    private JSONObject vpncustomerAdd;
    private JSONObject vpncustomerDel;
    private JSONObject vpncustomerUpdate;

    //用户协议
    private JSONObject vpnprotocolGet;
    private JSONObject vpnprotocolAdd;
    private JSONObject vpnprotocolUpdate;
    private JSONObject vpnprotocolDel;

    //提现协议
    private JSONObject vpnwithdrawRuleGet;
    private JSONObject vpnwithdrawRuleAdd;
    private JSONObject vpnwithdrawRuleUpdate;
    private JSONObject vpnwithdrawRuleDel;

    //投诉
    private JSONObject vpncomplaintGet;
    private JSONObject vpncomplaintAdd;
    private JSONObject vpncomplaintDel;

    //攻略
    private JSONObject vpnstrategyGet;
    private JSONObject vpnstrategyAdd;
    private JSONObject vpnstrategyUpdate;
    private JSONObject vpnstrategyDel;

    //积分
    private JSONObject vpnPointAdd;

    //礼物
    private JSONObject vpnGiftAdd;

    //礼物兑换
    private JSONObject vpnGiftChange;

//    /**
//     * JSON表达的开通派发线路信息
//     */
//    private JSONArray opDetail;
    /**
     * 可选保留字段，双方暂时不对内容进行约定
     */
    private String reserved;
}
