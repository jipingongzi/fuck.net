package com.example.service.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by ibm on 2016/1/11.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {

    /**
     * 接口状态返回值，正常为0
     */
    private int apiStatus;

    /**
     * 账号状态返回值，正常为0
     */
    private int sysStatus;

    /**
     * 接口返回对象值
     */
    private T data;

    /**
     * 接口返回提示信息
     */
    private String info = "";

    private long timestamp = System.currentTimeMillis();

    @SuppressWarnings("unchecked")
    public static <T> RestResponse<T> buildRestResponse(T t) throws IOException {
        RestResponse restResponse = new RestResponse();

        if (t == null) {
            restResponse.setData(new HashMap<String, String>());
        } else if (t instanceof String) {
            restResponse.setInfo(t.toString());
        }  else {
            restResponse.setData(t);
        }

        return restResponse;
    }

    public static RestResponse<Object> buildResponse(Object data, int apiStatus) {
        RestResponse<Object> restResponse = new RestResponse<>();
        restResponse.setData(data);
        restResponse.setApiStatus(apiStatus);
        return restResponse;
    }

    public static RestResponse buildResponse(Object data, int apiStatus, String info) {
        RestResponse restResponse = new RestResponse();
        restResponse.setData(data);
        restResponse.setApiStatus(apiStatus);
        restResponse.setInfo(info);
        return restResponse;
    }

    public static RestResponse buildResponse(Object data) {
        RestResponse restResponse = new RestResponse();
        restResponse.setData(data);
        restResponse.setApiStatus(0);
        return restResponse;
    }

    public static RestResponse buildResponse(String info) {
        RestResponse restResponse = new RestResponse();
        restResponse.setInfo(info);
        restResponse.setApiStatus(0);
        return restResponse;
    }

    public static RestResponse buildExceptionResponse(String errorMessage) {
        RestResponse restResponse = new RestResponse();
        restResponse.setInfo(errorMessage);
        restResponse.setApiStatus(-1);
        return restResponse;
    }

    public int getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(int apiStatus) {
        this.apiStatus = apiStatus;
    }

    public int getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(int sysStatus) {
        this.sysStatus = sysStatus;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
