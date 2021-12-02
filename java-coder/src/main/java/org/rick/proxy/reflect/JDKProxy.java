package org.rick.proxy.reflect;


import org.rick.proxy.reflect.factory.CacheService;
import org.rick.proxy.reflect.factory.impl.CacheServiceImpl;
import org.rick.proxy.reflect.factory.impl.EGMCacheAdapter;
import org.rick.proxy.reflect.factory.impl.IIRCacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {

    public static <T> T getProxy(Class<T> interfaceClass, CacheService cacheAdapter) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, classes, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return CacheService.class.getMethod(method.getName(), method.getParameterTypes())
                        .invoke(cacheAdapter, args);
            }
        });
    }

    public static void main(String[] args) throws Exception {

        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "nb");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "hello");
        String val02 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + val02);
    }

}
