package com.cash.spring.aop.plugin.plugin.provider;

import com.cash.spring.aop.plugin.plugin.Execution;
import com.cash.spring.aop.plugin.plugin.PluginProvider;
import com.cash.spring.aop.plugin.plugin.meta.BeanPluginMeta;
import com.cash.spring.aop.plugin.plugin.meta.PluginMeta;
import com.cash.spring.aop.plugin.utils.ReflectionUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置化插件
 * author cash
 * create 2017-10-10-9:31
 **/

public class ConfigurablePluginProvider implements PluginProvider,InitializingBean,ApplicationContextAware {
    private Logger logger= LoggerFactory.getLogger(ConfigurablePluginProvider.class);

    private Map<String,Map<Long,PluginMeta>> pluginMap=new HashMap<>();
    private Resource[] locations;
    private ApplicationContext applicationContext;



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(null!=locations && locations.length>0){
            for(Resource pluginConfig:locations){
                if(logger.isDebugEnabled()){
                    logger.debug(String.format("开始加载插件配置文件:%s",pluginConfig.getFilename()));
                }
                loadPluginConfig(pluginConfig);
            }
        }

    }

    public void loadPluginConfig(Resource config){
        SAXReader saxReader=new SAXReader();
        try {
            Document document=saxReader.read(config.getInputStream());
            Element root=document.getRootElement();
            List elements=root.elements("plugin");
            if(CollectionUtils.isNotEmpty(elements)){
                for(Object element:elements){
                    Element internal= (Element) element;
                    BeanPluginMeta pluginMeta=new BeanPluginMeta();
                    String signature=internal.elementTextTrim("signature");
                    if(StringUtils.isEmpty(signature)){
                        throw new RuntimeException(config.getFilename()+" plugin's signature is empty");
                    }
                    pluginMeta.setSignature(signature);
                    String isSpringBean=internal.elementTextTrim("isSpringBean");
                    if(StringUtils.isNotEmpty(isSpringBean)){
                        pluginMeta.setSpringBean(Boolean.parseBoolean(isSpringBean));
                    }
                    String beanName=internal.elementTextTrim("beanName");
                    pluginMeta.setBeanName(beanName);
                    String beanClass=internal.elementTextTrim("beanClass");
                    pluginMeta.setBeanClass(beanClass);

                    String method=internal.elementTextTrim("method");
                    if(StringUtils.isNotEmpty(beanName)){
                        Object bean=applicationContext.getBean(beanName);
                        if(null==bean){
                            logger.error("the bean:"+beanName+" in "+config.getFilename()+" is not exist");
                            continue;
                        }
                        Method declaredMethod= ReflectionUtils.getDeclaredMethod(bean.getClass(),method);
                        pluginMeta.setMethod(declaredMethod);
                    }else if(StringUtils.isEmpty(beanClass)){
                        Class<?> clazz = Class.forName(beanClass);
                        if (clazz == null) {
                            logger.error("the beanClass:"+beanClass+" in "+config.getFilename()+" is not exist");
                            continue;
                        }
                        Method declaredMethod = ReflectionUtils.getDeclaredMethod(clazz, method);
                        pluginMeta.setMethod(declaredMethod);
                    }
                    String execution=internal.elementTextTrim("execution");
                    if(StringUtils.isNotEmpty(execution)){
                        pluginMeta.setExecution(Execution.valueOf(execution.toUpperCase()));
                    }
                    String companyStr=internal.elementTextTrim("companyId");
                    Long companyId=ALL_COMPANY;
                    if(StringUtils.isNotEmpty(companyStr)){
                        String[] companyIdArr=companyStr.split(",");
                        for(String s:companyIdArr){
                            pluginMeta.setCompanyId(Long.parseLong(s));
                            addPlugin(pluginMeta);
                        }
                    }else{
                        pluginMeta.setCompanyId(companyId);
                        addPlugin(pluginMeta);
                    }
                }
            }
        } catch (DocumentException e) {
            logger.error("加载插件配置文件异常",e);
        } catch (IOException e) {
            logger.error("加载插件配置文件异常",e);
        } catch (ClassNotFoundException e) {
            logger.error("加载插件配置文件异常",e);
        }
    }

    public void addPlugin(PluginMeta pluginMeta){
        String firstKey=pluginMeta.getSignature()+pluginMeta.getExecution();
        Long secondkey=pluginMeta.getCompanyId();
        Map<Long,PluginMeta> map=pluginMap.get(firstKey);
        if(null==map){
            map=new HashMap<>();
        }
        map.put(secondkey,pluginMeta);
        pluginMap.put(firstKey,map);
    }

    @Override
    public PluginMeta getPluginMeta(Long companyId, String signature, Execution execution) {
        String key=signature+execution;
        if(pluginMap.containsKey(key)){
            Map<Long,PluginMeta> map=pluginMap.get(key);
            if(map.containsKey(companyId)){
                return map.get(companyId);
            }else if(map.containsKey(ALL_COMPANY)){
                return map.get(ALL_COMPANY);
            }
        }
        return null;
    }

    public void setLocations(Resource[] locations) {
        this.locations = locations;
    }
}
