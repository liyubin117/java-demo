package org.rick.proxy.reflect;


import org.rick.proxy.reflect.factory.CacheService;
import org.rick.proxy.reflect.factory.impl.EGMCacheAdapter;
import org.rick.proxy.reflect.factory.impl.IIRCacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JDKProxy {

    public static CacheService getProxy(CacheService cacheAdapter) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return (CacheService) Proxy.newProxyInstance(classLoader, cacheAdapter.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("proxy " + method.getName() + " begin");
                Object result = CacheService.class.getMethod(method.getName(), method.getParameterTypes())
                        .invoke(cacheAdapter, args);
                System.out.println("proxy " + method.getName() + " finished");
                return result;
            }
        });
    }

    public static void main(String[] args) throws Exception {

        CacheService proxy_EGM = JDKProxy.getProxy(new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "nb");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy(new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "hello");
        String val02 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + val02);
    }

}
