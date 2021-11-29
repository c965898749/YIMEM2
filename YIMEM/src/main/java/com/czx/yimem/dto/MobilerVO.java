package com.czx.yimem.dto;

import lombok.Data;

@Data
public class MobilerVO {
    private String phone;
    private String authCode;
    private Boolean rememberMe;

    private String code;
    private String password;
    private String inviteCode;

}
