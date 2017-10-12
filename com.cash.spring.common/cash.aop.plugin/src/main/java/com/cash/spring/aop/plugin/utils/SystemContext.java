package com.cash.spring.aop.plugin.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统上下文
 * author cash
 * create 2017-10-09-14:39
 **/

public class SystemContext {
    private static String COMPANY_ID_KEY = "companyId";
    private static String TRACE_ID_KEY = "traceId";
    private static transient ThreadLocal<Map<String, String>> contextMap = new ThreadLocal();
    private static Integer MAX_CAPACITY = Integer.valueOf(20);
    private static Integer MAX_SIZE = Integer.valueOf(200);

    public SystemContext() {
    }

    public static Map<String, String> getContextMap() {
        return (Map)contextMap.get();
    }

    public static void setContextMap(Map<String, String> ctxtMap) {
        contextMap.set(ctxtMap);
    }

    public static String get(String key) {
        Map contextMap = getContextMap();
        return contextMap == null?null:(String)contextMap.get(key);
    }

    public static String put(String key, String value) {
        if(key != null && value != null) {
            if(key.length() > MAX_SIZE.intValue()) {
                throw new RuntimeException("key is more than " + MAX_SIZE + ", i can\'t put it into the context map");
            } else if(value.length() > MAX_SIZE.intValue()) {
                throw new RuntimeException("value is more than " + MAX_SIZE + ", i can\'t put it into the context map");
            } else {
                Object contextMap = getContextMap();
                if(contextMap == null) {
                    contextMap = new HashMap();
                    setContextMap((Map)contextMap);
                }

                if(((Map)contextMap).size() > MAX_CAPACITY.intValue()) {
                    throw new RuntimeException("the context map is full, can\'t put anything");
                } else {
                    return (String)((Map)contextMap).put(key, value);
                }
            }
        } else {
            throw new RuntimeException("key:" + key + " or value:" + value + " is null,i can\'t put it into the context map");
        }
    }

    public static Long getCompanyId() {
        String companyId = get(COMPANY_ID_KEY);
        return companyId == null?null:(!"null".equalsIgnoreCase(companyId) && !"".equals(companyId)?Long.valueOf(companyId):null);
    }

    public static Long getDefaultCompanyId() {
        return Long.valueOf(-1L);
    }

    public static void setCompanyId(Long companyId) {
        if(companyId != null) {
            put(COMPANY_ID_KEY, companyId + "");
        }

    }

    public static String getTraceId(){
        String traceId=get(TRACE_ID_KEY);
        return traceId;
    }

    public static void setTraceId(String traceId){
        if(StringUtils.isNotEmpty(traceId)){
            put(TRACE_ID_KEY,traceId);
        }
    }

    public static void clean() {
        contextMap.remove();
    }
}
