package com.music.ui.entity;

/**
 * 类描述：封装API接口返回
 */
public class XResult<T> {
    public int code;   //返回码code.
    public String msg; //错误信息
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public void setData(T data) {
        this.data = data;
    }

}