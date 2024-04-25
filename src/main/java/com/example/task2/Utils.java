package com.example.task2;

//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Utils {
    public static <C> C cache(C objectToCache) {
//        System.out.println("Some Object of " + objectToCache.toString() + " is to be cached");
        return (C) Proxy.newProxyInstance(
                objectToCache.getClass().getClassLoader(),
                objectToCache.getClass().getInterfaces(),
                new CachingHandler<>(objectToCache));
    }
}

//class Inv implements InvocationHandler{
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("before ovrrdd invoke");
//        Object result = method.invoke(proxy, args);
//        System.out.println("after ovrrdd invoke");
//        return result;
//    }
//}