package com.czx.yimem.entity;

import lombok.Data;

@Data
public class AccessToken {
    private String token;
    private String expiresIn;
}
