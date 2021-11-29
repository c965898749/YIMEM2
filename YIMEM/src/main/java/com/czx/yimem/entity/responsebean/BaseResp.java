package com.czx.yimem.entity.responsebean;
import lombok.Data;

import java.io.Serializable;
@Data
public class BaseResp<T> implements Serializable {
    private int code ;  //1代表成功   0 代表失败
    private String msg ;
    private T data ;

    public BaseResp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public  BaseResp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResp(int code) {
        this.code = code;
    }

    public BaseResp() {
    }
}
