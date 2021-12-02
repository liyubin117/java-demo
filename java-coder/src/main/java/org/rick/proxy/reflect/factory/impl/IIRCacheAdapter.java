package org.rick.proxy.reflect.factory.impl;

import org.rick.proxy.reflect.factory.CacheService;
import org.rick.proxy.reflect.model.IIR;

import java.util.concurrent.TimeUnit;

public class IIRCacheAdapter implements CacheService {

    private IIR iir = new IIR();

    public String get(String key) {
        return iir.get(key);
    }

    public void set(String key, String value) {
        iir.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        iir.setExpire(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        iir.del(key);
    }

}
