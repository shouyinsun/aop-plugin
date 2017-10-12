package com.cash.spring.aop.plugin.plugin.meta;

import com.cash.spring.aop.plugin.plugin.Execution;

/**
 * 插件描述抽象父类
 * author cash
 * create 2017-10-09-20:26
 **/

public abstract class PluginMeta {
    /**
     * companyId
     */
    private Long companyId;

    /**
     * 代理方法元信息
     */
    private String signature;

    /**
     * 代理协议
     */
    private String protocol;

    /**
     * 执行方法
     */
    private Execution execution=Execution.REPLACE;

    private boolean proceedingOnError=false;

    public abstract  String getProtocol();

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    public boolean isProceedingOnError() {
        return proceedingOnError;
    }

    public void setProceedingOnError(boolean proceedingOnError) {
        this.proceedingOnError = proceedingOnError;
    }

    @Override
    public String toString() {
        return "PluginMeta{" +
                "companyId=" + companyId +
                ", signature='" + signature + '\'' +
                ", protocol='" + getProtocol() + '\'' +
                ", execution=" + execution +
                ", proceedingOnError=" + proceedingOnError +
                '}';
    }
}
