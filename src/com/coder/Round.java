package com.coder;
import java.io.*;

/**
 若小数点后数值大于等于5，向上取整；否则向下取整
 */
public class Round {
    public static void main(String[] args) throws IOException {
        // 将输入的浮点型视为字符串处理，找到 . 后第一个字符，转换成数字后与5比较
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int index = s.indexOf(".");
        int a =Integer.parseInt(s.substring(0,index));
        int b=Integer.parseInt(s.substring(index+1,index+2));
        if (b >=5) {
            System.out.println(a+1);
        }else {
            System.out.println(a);
        }

    }
}
