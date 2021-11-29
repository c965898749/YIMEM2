package com.czx.yimem.utils;

public class Constants {
    public static final String NO_NULL="不能为空";
    public static final String ZMAP="QS_new";
    public static final String QS_ISREAD="QS_ISREAD";
    public static final String NO_AUTH="您无权查看";
    public static final String NO_HANDLE="您无权操作";
    public static final Integer EN_MONEY_COUNT=10;//最大提现次数
    public static final Integer EN_Nav_COUNT=4;//导航最大数量
    public static final String ORDER_ON = "JHNO";
    public static final String NOT_FOUND="暂无资源";
    public static final String EXCEPTION="服务器异常请重试";
    public static final String SIGNED="今日已签到成功";
    public static final String NO_MONEY="余额不足";
    public static final String NO_POINTS="积分不足";
    public static final String NO_GIFT="礼物不存在";
    public static final String SUCCEE="成功";
    public static final String FAIL="失败";
    public static final String FAIL_UPLOAD="上传失败";
    public static final String OVERDUE_TOKEN="token已过期或不存在,请重新登录";
    public static final String OVERDUE_NOTE="验证码错误或过期";
    public static final String NO_FOUND_USER="用户不存在，请重新登录";
    public static final String NO_RESUME="你没有简历可投，请完善简历";
    public static final String NO_FOUND_PHNONE="该手机号未注册、请联系管理员";
    public static final String YE_FOUND_PHNONE="该手机号已绑定过微信，请更换手机号绑定";
    public static final String NO_INVIRECODE="请输入正确邀请码";
    public static final String YE_FOUND_USER="微信或手机号已绑定，请重新注册";
    public static final String NO_FOUND_OPENID="请用微信登录";
    public static final String YE_FOUND_OPENID="微信号未绑定手机号，请绑定手机号";
    public static final String LOGIN_FALL="登录失败！请检查用户名和密码";
    public static final String YE_SEND="你已投递，无需再投";
    public static final String U_to_U="不能给我自己发消息或通知";
    public static final String VERIFY_FAIL="验证失败";
    public static final String NO_STIPULATE="不合规";
    public static final String VERIFY_SUCCEE="验证成功";
    public static final Integer SUCCEE_CODE=1;
    public static final Integer FAIL_CODE=0;
    public static final Integer OVER_CODE=403;
    public static final String UNAUTHORIZED="权限不足";
    public static final String FORBIDDEN="权限不足";
    public static final String YE_GROUP="存在子部门时候不可删除";
    public static final String YE_MEMBER="存在员工时候不可删除";
    public static final String CONTENT_TYPE="text/plain;charset=UTF-8";
    public static final Integer DEFAULT_ROLE=1;
    public static final Integer ADMIN_ROLE=0;
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    public static final String APPID="33";
    public static final String SECRET="22";
    public static String OSS_URL="33";

    /**
     * 登录的地址
     */
    public static final String AUTH_LOGIN_URL = "/auth/login";

    /**
     * 角色的key
     **/
    public static final String ROLE_CLAIMS = "rol";
    /**
     * rememberMe 为 false 的时候过期时间是1个小时
     */
    public static final long EXPIRATION = 60 * 60L;
    /**
     * rememberMe 为 true 的时候过期时间是30天
     */
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 30L;


    /**
     * 入职满多少天发放佣金
     */
    public static final Integer ENTRY_TIME = 1;

    /**
     * JWT签名密钥硬编码到应用程序代码中，应该存放在环境变量或.properties文件中。
     */
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    // JWT token defaults

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

}
