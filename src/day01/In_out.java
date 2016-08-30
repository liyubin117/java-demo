package day01;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2015-9-17.
 * 输入、输出
 */
public class In_out {
    public static void main(String args[]) throws IOException {
    /*    Scanner scanner1 = new Scanner(System.in);
        //输入含空格的字符串
        System.out.println("enter your name:");
        String s1 = scanner1.nextLine();
        System.out.println("name:"+s1);
        //输入不含空格的字符串
        System.out.println("enter a word:");
        String s2 = scanner1.next();
        System.out.println(s2);
        //输入整型
        System.out.println("enter a integer:");
        int a1 = scanner1.nextInt();
        System.out.println("integer:"+a1);
        //输入double型
        System.out.println("enter a double:");
        double d1 = scanner1.nextDouble();
        System.out.println("double:"+d1);*/

        //输入密码,使用Console类
        Console c1 = System.console();
        if (c1 != null){
            String username = c1.readLine("user name:");
            char[] password = c1.readPassword("password:");
            c1.printf("user name:"+username+" ");
            c1.printf("password:"+password);
        }
        else{
            System.out.println("console is not available");
        }

        //输入、输出文件
        Scanner in = new Scanner(Paths.get("F:\\java\\HelloWorld.java"));
        String s5 = null;
       do{
           s5 = in.nextLine();
           System.out.println(s5);
       } while( (in.nextLine()) != null );


        in.close();

        PrintWriter out = new PrintWriter("F:\\java\\new.java");







    }

}
