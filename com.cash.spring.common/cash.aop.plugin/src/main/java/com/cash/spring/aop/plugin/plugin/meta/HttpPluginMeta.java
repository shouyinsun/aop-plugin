package com.cash.spring.aop.plugin.plugin.meta;

import com.cash.spring.aop.plugin.plugin.PluginProtocol;

/**
 * http协议元信息
 * author cash
 * create 2017-10-09-20:32
 **/

public class HttpPluginMeta extends PluginMeta {
    //http请求地址
    private String url;
    //http请求方法get,post
    private String method = "POST";
    //http请求数据传输类型xml,json,default
    private String dataType;
    //返回结果类型
    private String response;

    @Override
    public String getProtocol() {
        return PluginProtocol.http.name();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


}
