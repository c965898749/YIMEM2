package com.czx.yimem.entity.requestbean;

import lombok.Data;

@Data
public class Signpramer<T> {
    private T json;
    private String sign;
    private String code;

}
