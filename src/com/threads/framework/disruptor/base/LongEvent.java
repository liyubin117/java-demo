package com.threads.framework.disruptor.base;

public class LongEvent {
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    private long value;
}
