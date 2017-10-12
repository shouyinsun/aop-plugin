package com.cash.spring.aop.plugin.plugin;

import com.cash.spring.aop.plugin.plugin.meta.PluginMeta;

/**
 * 插件提供
 * author cash
 * create 2017-10-09-20:40
 **/

public interface PluginProvider {

    Long ALL_COMPANY = -1L;

    /**
     * 获取公司插件信息<br>*
     * @param companyId 公司Id
     * @param signature 方法元信息
     * @return 插件信息
     */

    PluginMeta getPluginMeta(Long companyId, String signature, Execution execution);
}
