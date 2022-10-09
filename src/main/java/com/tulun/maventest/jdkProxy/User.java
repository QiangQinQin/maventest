package com.tulun.maventest.jdkProxy;

/**
 * 委托类：具体实现接口中方法
 */
public class User implements IUser {
    @Override
    public void talk() {
        System.out.println("User类中 实现 talk方法");
    }
}