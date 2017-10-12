package com.cash.spring.aop.plugin.servicePlugin;

import com.alibaba.fastjson.JSONObject;
import com.cash.spring.aop.plugin.dto.XxDto;
import com.cash.spring.aop.plugin.plugin.Execution;
import com.cash.spring.aop.plugin.plugin.annotation.Plugin;
import com.cash.spring.aop.plugin.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 插件service
 * author cash
 * create 2017-10-11-10:05
 **/

@Service
public class TestServicePlugin {

    Logger logger= LoggerFactory.getLogger(TestServicePlugin.class);

    /*@Plugin(target = TestService.class,method = "ForReplacePlugin",execution = Execution.REPLACE,companyId ={10,20},proceedingOnError = false)*/
    public void ForReplacePlugin(XxDto xxDto){
        logger.info("-----------replace begin");
        System.out.println("dto is:"+ JSONObject.toJSON(xxDto));
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
    }

    @Plugin(target = TestService.class,execution = Execution.BEFORE,companyId ={10,20},proceedingOnError = true)
    public void ForBeforePlugin(XxDto xxDto){
        logger.info("-----------before begin");
        System.out.println("dto is:"+JSONObject.toJSON(xxDto));
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
        throw new NullPointerException("nullPointerException");
    }

    @Plugin(target = TestService.class,execution = Execution.AFTER,companyId ={10,20})
    public void ForAfterPlugin(XxDto xxDto){
        logger.info("-----------after begin");
        System.out.println("dto is:"+JSONObject.toJSON(xxDto));
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
    }

    @Plugin(target = TestService.class,execution = Execution.AFTER,companyId ={10,20})
    public int ForAfterPlugin2(int i,XxDto xxDto){
        logger.info("-----------the i is: "+i);
        logger.info("-----------after begin");
        System.out.println("dto is:"+JSONObject.toJSON(xxDto));
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
        i=1000;
        return i;
    }


}
