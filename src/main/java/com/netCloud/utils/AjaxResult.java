package com.netCloud.utils;

/**
 * Created by dllo on 17/12/6.
 */
public class AjaxResult {

    private String code, msg;
    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(Object data) {
        this.code = "0";
        this.msg = "成功";
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
