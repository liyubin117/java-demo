package com.useful;

import io.airlift.units.Duration;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AirfliftDemo {
    private Duration duration;

    @Before
    public void setUp(){
        duration = new Duration(5, TimeUnit.SECONDS);
    }

    @Test
    public void testSleep(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
            System.out.println(sdf.format(new Date()));
            TimeUnit.SECONDS.sleep((long)duration.getValue(TimeUnit.SECONDS));
            System.out.println(sdf.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
