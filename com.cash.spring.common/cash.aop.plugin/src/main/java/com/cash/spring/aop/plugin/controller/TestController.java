package com.cash.spring.aop.plugin.controller;

import com.cash.spring.aop.plugin.dto.XxDto;
import com.cash.spring.aop.plugin.service.TestService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * controller类
 * author cash
 * create 2017-10-09-16:06
 **/
@Controller
public class TestController {

    private Logger logger= LoggerFactory.getLogger(TestController.class);

    private final com.squareup.okhttp.OkHttpClient client = new com.squareup.okhttp.OkHttpClient();

    public final com.squareup.okhttp.MediaType APPLICATION_JSON = com.squareup.okhttp.MediaType.parse("application/json; charset=utf-8");

    @Autowired
    TestService testService;



    @RequestMapping("/test.do")
    public @ResponseBody String ForTest(){
        XxDto xxDto=new XxDto();
        xxDto.setName("cash");
        xxDto.setAge(20);
        testService.ForReplacePlugin(xxDto);
        return "well done";
    }

    @RequestMapping("/test1.do")
    public @ResponseBody String ForTest1(){
        XxDto xxDto=new XxDto();
        xxDto.setName("cash");
        xxDto.setAge(20);
        testService.ForBeforePlugin(xxDto);
        return "well done";
    }

    @RequestMapping("/test2.do")
    public @ResponseBody String ForTest2(){
        XxDto xxDto=new XxDto();
        xxDto.setName("cash");
        xxDto.setAge(20);
        testService.ForAfterPlugin(xxDto);
        return "well done";
    }

    @RequestMapping("/test3.do")
    public @ResponseBody String ForTest3(){
        XxDto xxDto=new XxDto();
        xxDto.setName("cash");
        xxDto.setAge(20);
        int i=testService.ForAfterPlugin2(xxDto);
        System.out.println("the return value is: "+i);
        return "well done";
    }


    @RequestMapping("/okHttp.do")
    public void testOkHttp(){

        ProviderSendRequest providerSendRequest = new ProviderSendRequest();
        Map<String, String> mapParams=new HashMap<>();
        mapParams.put("messageId", "23549234052391243");
        mapParams.put("recordId", "afasdfqwerawerqwe");
        String url="http://127.0.0.1:8094/xwProviderService/send";
        providerSendRequest.setParams(mapParams);
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(APPLICATION_JSON, com.alibaba.fastjson.JSON.toJSONString(providerSendRequest)))
                .build();
        client.setConnectTimeout(10L, TimeUnit.SECONDS);
        client.setWriteTimeout(10L, TimeUnit.SECONDS);
        client.setReadTimeout(10L, TimeUnit.SECONDS);

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                StringBuilder errorMsg = new StringBuilder();
                errorMsg.append(request);
                errorMsg.append("|");
                errorMsg.append(e);
                System.out.println("onFailure,errorMsg:"+errorMsg);
            }

            @Override
            public void onResponse(Response response) {
                /*调用成功处理*/
                try {
                    if (response.isSuccessful()) {
                        String responseBodyStr=response.body().string();
                        System.out.println("onResponse ,responseBodyStr: "+responseBodyStr);
                    } else {
                        logger.warn(String.format("发送短信内容失败,Response:%s", response));
                    }
                } catch (Exception ex) {
                    logger.error(String.format("发送短信内容异常,Response:%s", response), ex);
                }
            }
        });

    }

    class ProviderSendRequest {
        @JsonProperty("params")
        private Map<String, String> params;

        public Map<String, String> getParams() {
            return params;
        }

        public void setParams(Map<String, String> params) {
            this.params = params;
        }
    }



}
