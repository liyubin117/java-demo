package org.rick.proxy.reflect.factory.impl;

import org.rick.proxy.reflect.factory.CacheService;
import org.rick.proxy.reflect.model.EGM;

import java.util.concurrent.TimeUnit;

public class EGMCacheAdapter implements CacheService {

    private EGM egm = new EGM();

    public String get(String key) {
        return egm.gain(key);
    }

    public void set(String key, String value) {
        egm.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        egm.setEx(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        egm.delete(key);
    }
}
