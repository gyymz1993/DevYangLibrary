package com.utils.gyymz.http.callback;


import com.utils.gyymz.http.subscriber.ResponseHandler;

import java.io.Serializable;


public class DataBeanCallBack<T> implements Serializable, ResponseHandler.IBaseData {
    public int code;
    public String msg;
    public T data;

    public boolean isSuccess() {
        return code == 0;
    }

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

    @Override
    public int code() {
        return code;
    }

    @Override
    public Object data() {
        return data;
    }

    @Override
    public String msg() {
        return msg;
    }
}
