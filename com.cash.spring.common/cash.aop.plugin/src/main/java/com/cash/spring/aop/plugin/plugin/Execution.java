package com.cash.spring.aop.plugin.plugin;

/**
 * 执行方式
 * author cash
 * create 2017-10-09-20:11
 **/

public enum Execution {
    REPLACE(0),
    BEFORE(1),
    AFTER(2);
    private final int value;

    Execution(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

}
