package com.tulun.maventest.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理 辅助类  （不是代理类，代理类是动态产生的，不是我们自己实现的）
 * 要实现动态代理，则需要给定一个  实现了invocationHandler接口的 实现类，该类中需要持有IUser实现类的引用
 */
public class UserProxy implements InvocationHandler {
    private IUser user;

    //必须持有 IUser接口的引用 (要和委托类持有同一个接口)
    public UserProxy(IUser user) {
        this.user = user;
    }

    /**
     * 实现InvocationHandler接口
     * 本质上是要重写invoke方法
     * @param proxy ：是动态代理产生的代理对象
     * @param method：代理对象 要调用的 委托类的方法
     * @param args ：表示 方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理类除了实现原有类的方法，还可以添加新的功能  （类比AOP 前置  后置  环绕通知）
        System.out.println("委托类自定义业务逻辑1");

        //会自动调用到  委托类 即User的方法
        method.invoke(user, args);

        System.out.println("委托类自定义业务逻辑2");
        return null;
    }


    public static void main(String[] args) {
//        将JDK动态代理生成的类保存为 .class文件
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        /**
         * 参数列表：
         * ClassLoader loader,指定当前代理类的加载器
         * Class<?>[] interfaces,当前被代理的接口 IUser
         * InvocationHandler h ：代理辅助类 UserProxy
         */
        //产生的  代理类
        IUser user = (IUser) Proxy.newProxyInstance( UserProxy.class.getClassLoader(),  new Class[]{IUser.class},  new UserProxy(new User()));
        user.talk();  //实际上调用的是UserProxy里的invoke方法
    }
}