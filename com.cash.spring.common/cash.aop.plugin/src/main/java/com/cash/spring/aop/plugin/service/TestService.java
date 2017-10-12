package com.cash.spring.aop.plugin.service;

import com.alibaba.fastjson.JSONObject;
import com.cash.spring.aop.plugin.dto.XxDto;
import com.cash.spring.aop.plugin.plugin.annotation.Plugable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * service类
 * author cash
 * create 2017-10-09-16:07
 **/

@Service
public class TestService {
    Logger logger= LoggerFactory.getLogger(TestService.class);

    @Plugable
    public void ForReplacePlugin(XxDto xxDto){
        logger.info("-----------replace begin");
        System.out.println("dto is:"+JSONObject.toJSON(xxDto));
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
    }

    @Plugable
    public void ForBeforePlugin(XxDto xxDto){
        logger.info("-----------before begin");
        System.out.println("dto is:"+JSONObject.toJSON(xxDto));
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
    }

    @Plugable
    public void ForAfterPlugin(XxDto xxDto){
        logger.info("-----------after begin");
        System.out.println("dto is:"+JSONObject.toJSON(xxDto));
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
    }

    @Plugable
    public int ForAfterPlugin2(XxDto xxDto){
        logger.info("-----------after begin");
        System.out.println("dto is:"+JSONObject.toJSON(xxDto));
        xxDto.setAge(25);
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
        return 100;
    }
}
