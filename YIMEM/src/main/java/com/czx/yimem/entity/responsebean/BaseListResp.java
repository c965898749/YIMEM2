package com.czx.yimem.entity.responsebean;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseListResp<T> implements Serializable {
    private int success ;  //1代表成功   0 代表失败
    private String resultcode ;
    private String msg ;
    private T data ;
}
