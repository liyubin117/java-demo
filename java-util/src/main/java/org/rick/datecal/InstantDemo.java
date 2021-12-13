package org.rick.datecal;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author Yubin Li
 * @date 2021/12/10 11:24
 **/
public class InstantDemo {
    public static void main(String[] args) {
        //instant 相当于 date
        Instant instant = Instant.now();
        System.out.println(instant);
        Date date = new Date();
        System.out.println(date);
        //instant转date 类方法（java.util.date）
        Date from = Date.from(instant);
        System.out.println(date);
        //datet转instant 对象方法（java.util.date）
        Instant instant1 = date.toInstant();
        System.out.println(instant1);
        //instant 根据毫秒值或者date转换为instant 类方法 （java.time）
        Instant instant2 = Instant.ofEpochMilli(date.getTime());
        System.out.println(instant2);
        //instant 根据秒值或者date转换为instant 类方法 （java.time）
        Instant instant3 = Instant.ofEpochSecond(60 * 60L);
        Instant instant4 = Instant.ofEpochSecond(60 * 60 * 24L);
        System.out.println(instant3);
        System.out.println(instant4);
        //instant 第一个参数指定秒数，第二个单位指定纳秒数，这样得到的instant会包含纳秒的数据 1000000000纳秒（9位）=1秒
        Instant instant5 = Instant.ofEpochSecond(60 * 60 * 24L,  1000000000*60L);
        System.out.println(instant5);
        //instant 将字符串类型的instant转换为instantd对象，如：1970-01-02T00:01:00Z，但是date的结果字符串不可以转换，会报错DateTimeParseException
        //注意：必须传入的是符合 UTC格式的字符串
        Instant parse = Instant.parse("1970-01-01T01:00:00Z");
        System.out.println(parse+"parse");
        //instant 在现有的instant的时间上追加些时间，下面例子追加了5小时10分钟，这里plus会产生新的instant对象
        Instant plus = instant.plus(Duration.ofHours(5).plusMinutes(10));
        System.out.println(instant+" instant "+plus+"plus");
        System.out.println(instant == plus);//plus会产生新的instant对象 所以结果位false
        //instant 获取其5天前的instant(此刻)
        Instant minus = instant.minus(5, ChronoUnit.HOURS);
        System.out.println(instant+" instant "+minus+" minus");
        //也可以直接调用相关减法方法,效果跟上面的方法一样
        Instant instant6 = instant.minusSeconds(60 * 60 * 5);
        System.out.println(instant+" instant "+instant6+" instant6");
        //减法方法,效果跟上面的方法一样
        Instant minus1 = instant.minus(Duration.ofHours(5));
        System.out.println(instant+" instant "+minus1+" minus1");
        //计算两个Instant之间的秒数， ChronoUnit用的什么，得到的结果就是什么单位
        System.out.println(instant+" instant "+instant6+" instant6");
        long between = ChronoUnit.SECONDS.between(instant6, instant);
        System.out.println(between);
        //比较两个instant 相等 0， 前者时间纳秒值大于后者 1，小于后者 -1或小于0
        int i = instant.compareTo(instant6);
        System.out.println(i);
        //判断instant时间前后，前者在后者之后返回true,反之false
        boolean after = instant.isAfter(instant6);
        System.out.println(after);
        //判断instant时间前后，前者在后者之前返回true,反之false，正好与上面相反
        boolean before = instant.isBefore(instant6);
        System.out.println(before);
    }

    @Test
    public void testInstant2Timestamp() {
        System.out.println(Timestamp.from(Instant.now()));
    }
}
