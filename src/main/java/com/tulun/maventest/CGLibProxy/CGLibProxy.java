package com.tulun.maventest.CGLibProxy;

/**
 * @author QiangQin
 * * @date 2021/8/4
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 产生CGlib代理对象的 辅助类
     * 实现MethodInterceptor
     * 该接口中的intercept方法需要重写
 */
public class CGLibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public <T> T  getProxy(Class<T> classz) {
        enhancer.setSuperclass(classz); //设置 父类
        enhancer.setCallback(this);   // 设置  回调对象
        return (T)enhancer.create();   //通过字节码技术创建 子类实例
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理辅助类的使用1");//  模拟自定义的业务逻辑

        Object o1 = methodProxy.invokeSuper(o, objects);// 调用父类CCGlibSuper的doing()

        System.out.println("代理辅助类的使用2");
        return o1;
    }


    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();
        CCGlibSuper proxy = cgLibProxy.getProxy(CCGlibSuper.class);     //产生代理对象
        proxy.doing();//调用到 CGLibProxy的 intercept方法，拦截父类CCGlibSuper的doing()并增强
    }
}
