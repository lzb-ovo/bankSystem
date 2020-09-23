package com.qfedu.common;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/3 16:39
 * description:
 */

public class JsonResult {
    private Integer code;
    private Object info;

    public JsonResult(Integer code, Object info) {
        this.code = code;
        this.info = info;
    }

    public JsonResult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
