package org.rick.datecal;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author Yubin Li
 * @date 2021/12/10 11:33
 **/
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        //LocalDateTime 相当于calendar
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime+" localDateTime1");
        //当前时间加上5小时，分钟等一样的用法，支持链式编程
        LocalDateTime localDateTime1 = localDateTime.plusHours(5);
        System.out.println(localDateTime1+" localDateTime1");
        //当前时间加上5小时，分钟等一样的用法，支持链式编程 但是这里localtime只是时间，不展示年月日，只展示如：15:26:50.398 时分秒毫秒
        LocalTime localDateTime2 = localDateTime.toLocalTime().plusHours(5);
        //当前时间加上5天，只展示年月日，不展示时分秒毫秒,下面是两种写法，都可以
        LocalDate localDate = localDateTime.toLocalDate().plusDays(5);
        System.out.println(localDateTime2+" localDateTime2 "+localDate+ " localDate");
        LocalDate plus = localDateTime.toLocalDate().plus(Period.ofDays(5));
        System.out.println(plus+"  plus");
    }

    @Test
    public void testTimeZone() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now()
                ,ZoneId.systemDefault());

        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss"));
        System.out.println(format);
    }
}
