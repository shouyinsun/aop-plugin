package com.cash.spring.aop.plugin.servicePlugin;

import com.alibaba.fastjson.JSONObject;
import com.cash.spring.aop.plugin.dto.XxDto;
import com.cash.spring.aop.plugin.plugin.Execution;
import com.cash.spring.aop.plugin.plugin.annotation.Plugin;
import com.cash.spring.aop.plugin.service.TestService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 插件service
 * author cash
 * create 2017-10-11-10:05
 **/

@Service
@Plugin(target = TestService2.class,execution = Execution.REPLACE,companyId ={30},proceedingOnError = false)
public class TestServicePlugin2 {

    Logger logger= LoggerFactory.getLogger(TestServicePlugin2.class);

    public void ForPlugin(XxDto xxDto){
        logger.info("-----------begin");
        System.out.println("dto is:"+ JSONObject.toJSON(xxDto));
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
    }


}
