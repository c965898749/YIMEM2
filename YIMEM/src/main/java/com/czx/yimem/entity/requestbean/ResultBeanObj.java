package com.czx.yimem.entity.requestbean;

public class ResultBeanObj<T> {

    private int success;
    private String resultcode;
    private String msg;
    private T data;



    private ResultBeanObj(int success, String msg, T data){
        this.success=success;
        this.msg=msg;
        this.data=data;
    }


    private ResultBeanObj(int success, String msg){
        this.success=success;
        this.msg=msg;
    }


    public String getResultcode() {
        return resultcode;
    }

    public ResultBeanObj setResultcode(String resultcode) {
        this.resultcode = resultcode;
        return this;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public ResultBeanObj setData(T data) {
        this.data = data;
        return this;
    }
    public static ResultBeanObj success(){
        ResultBeanObj msg = new ResultBeanObj(1, "成功");
        return msg;
    }

    public static ResultBeanObj fail(){
        ResultBeanObj msg = new ResultBeanObj(0, "系统异常");
        return msg;
    }

    public static ResultBeanObj fail(String failmsg){
        ResultBeanObj msg = new ResultBeanObj(0, failmsg);
        return msg;
    }

    public static ResultBeanObj ext(int success,String failmsg){
        ResultBeanObj msg = new ResultBeanObj(success, failmsg);
        return msg;
    }
}
