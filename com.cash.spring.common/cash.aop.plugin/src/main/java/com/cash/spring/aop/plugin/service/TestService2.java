package com.cash.spring.aop.plugin.service;

import com.cash.spring.aop.plugin.plugin.annotation.Plugable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * author cash
 * create 2017-10-10-20:02
 **/

@Service
public class TestService2 {
    Logger logger= LoggerFactory.getLogger(TestService2.class);

    @Plugable
    public void ForPlugin2(){
        logger.info("-----------begin");
        System.out.println(String.format("method invoke in %s",this.getClass().getName()));
    }
}
