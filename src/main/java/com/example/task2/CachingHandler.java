package com.example.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CachingHandler<C> implements InvocationHandler {
    private final C objectToCache;
    private final Map<Method, Object> results = new HashMap<>();
    public CachingHandler(C objectToCache) {
        System.out.println("Let us cache this object of: " + objectToCache);
        this.objectToCache = objectToCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object resultObject;
        Method currentMethod;
        currentMethod = objectToCache.getClass().getMethod(method.getName(), method.getParameterTypes());

        if (currentMethod.isAnnotationPresent(Cache.class)) {
            if (results.containsKey(currentMethod)) {
//                System.out.println("Cached already");
                return results.get(currentMethod);
            }
//            System.out.println("Object is not cached yet");
            resultObject = method.invoke(objectToCache, args);
            results.put(currentMethod, resultObject);
//            System.out.println("but cached now!");
            return resultObject;
        }

        if (currentMethod.isAnnotationPresent(Mutator.class)) {
//            System.out.println("Mutator: " + currentMethod.getName());
            results.clear();
        }
//        System.out.println("neither Cache nor Mutator " + currentMethod.getName()) + " let it go";
        return method.invoke(objectToCache, args);
    }
}
