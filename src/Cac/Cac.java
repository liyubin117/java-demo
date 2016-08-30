package Cac;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Administrator on 2015-10-13.
 * 日期、公历类
 * 其中的月份数字，是从0开始的，如12月份为11
 * 年、日期数字是从1开始的，如15号为15.
 * 星期日、一、二、三、四、五、六按1、2、3、4、5、6、7排列
 * 10月13日 星期二 依次用数字表示为： 9 13 3
 * @author liyubin
 */
public class Cac {
    public static void main(String args[]){
        //由于不同地区有不同的格式习惯（如每个星期的第一天是星期几），可设置适应哪个地区的习惯
        Locale.setDefault(Locale.GERMANY);
        GregorianCalendar d = new GregorianCalendar();
        System.out.println("德国星期的第一天是："+d.getFirstDayOfWeek());
        Locale.setDefault(Locale.CHINESE);
        GregorianCalendar d2 = new GregorianCalendar();
        System.out.println("中国星期的第一天是："+d2.getFirstDayOfWeek());

        System.out.println(",李");
        GregorianCalendar GC1 = new GregorianCalendar(1999,11,31);
        GregorianCalendar GC2 = new GregorianCalendar(1999, Calendar.DECEMBER,31,10,21,59);
        if (GC1.before(GC2)){
            System.out.println("GC1 is before than GC2");
        }

        GregorianCalendar Now = new GregorianCalendar();
        int year = Now.get(Calendar.YEAR);
        int month = Now.get(Calendar.MONTH);
        int weekday = Now.get(Calendar.DAY_OF_WEEK);
        int day = Now.get(Calendar.DAY_OF_MONTH);
        System.out.println("year:"+year+" month:"+month+" weekday:"+weekday+" day:"+day);

        //使用GregorianCalendar对象去置一个Date对象
        GregorianCalendar calendar = new GregorianCalendar(2015,10,13);
        Date hireDay = calendar.getTime();
        //使用Date对象去置一个GregorianCalendar对象
        GregorianCalendar GC3 = new GregorianCalendar();
        GC3.setTime(hireDay);
        System.out.println(GC3.get(Calendar.YEAR));

        Date d1 = new Date();
        System.out.println(d1.getTime()/1000/60/60/24/365);
    }
}
