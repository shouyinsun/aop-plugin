package com.cash.spring.aop.plugin.filter;

import com.cash.spring.aop.plugin.utils.SystemContext;
import com.cash.spring.aop.plugin.utils.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * 域过滤器
 * author cash
 * create 2017-10-09-14:27
 **/
@Component("domainFilter")
public class DomainFilter implements Filter {

    Logger logger= LoggerFactory.getLogger(DomainFilter.class);

    @Autowired
    RedisUtil<String,String> redisUtil;

    private final String COMPANY_ID = "CASH:COMPANY_ID";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UUID uuid= UUID.randomUUID();
        try {
            SystemContext.setTraceId(uuid.toString());
            //redisUtil.set(COMPANY_ID,"10",90000000);
            String companyId=redisUtil.get(COMPANY_ID);
            if(StringUtils.isEmpty(companyId)){
                companyId="-1";
            }
            SystemContext.setCompanyId(Long.parseLong(companyId));

            chain.doFilter(request, response);
        } catch (Exception var13) {
            logger.error("DomainFilter中运行异常", var13);
        } finally {
            SystemContext.clean();
            SystemContext.clean();
        }
    }

    @Override
    public void destroy() {

    }
}
