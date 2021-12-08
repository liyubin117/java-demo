package org.rick.datecal;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Administrator on 2015-10-13.
 * 打印某月日历
 */
public class CalendarTest {
    public static void main(String[] args){
        //设置地区为英国
        Locale.setDefault(Locale.ENGLISH);

        //设置当前日期
        System.out.println("请输入当前日期中的月份:");
        Scanner s1 = new Scanner(System.in);
        int set_month = s1.nextInt()-1;
        System.out.println("请输入当前是几号:");
        int set_day = s1.nextInt();

        //当前日期
        GregorianCalendar d = new GregorianCalendar();
        d.set(Calendar.MONTH,set_month);
        d.set(Calendar.DAY_OF_MONTH,set_day);
        int today = d.get(Calendar.DAY_OF_MONTH);
        int month = d.get(Calendar.MONTH);

        //当前月的首日
        d.set(Calendar.DAY_OF_MONTH,1);
        int weekday = d.get(Calendar.DAY_OF_WEEK);
        int firstDayOfWeek = d.getFirstDayOfWeek();

        int indent = 0;
        while (weekday != firstDayOfWeek){
            indent++;
            d.add(Calendar.DAY_OF_MONTH,-1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }

        String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
        do{
            System.out.printf("%4s", weekdayNames[weekday]);
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }
        while (weekday != firstDayOfWeek);
        System.out.println();
        for (int i=1;i<=indent;i++){
            System.out.print("    ");
        }

        d.set(Calendar.DAY_OF_MONTH,1);
        do{
            int day = d.get(Calendar.DAY_OF_MONTH);
            System.out.printf("%3d", day);

            if (day == today) {
                System.out.print("*");
            }
            else{
                System.out.print(" ");
            }

            d.add(Calendar.DAY_OF_MONTH,1);
            weekday = d.get(Calendar.DAY_OF_WEEK);

            if (weekday == firstDayOfWeek){
                System.out.println();
            }
        }while (d.get(Calendar.MONTH) == month);

        if (weekday != firstDayOfWeek){
            System.out.println();
        }
    }
}
