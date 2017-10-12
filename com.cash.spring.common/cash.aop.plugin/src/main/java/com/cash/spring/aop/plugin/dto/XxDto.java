package com.cash.spring.aop.plugin.dto;

/**
 * author cash
 * create 2017-10-11-10:06
 **/

public class XxDto {
    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "XxDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
