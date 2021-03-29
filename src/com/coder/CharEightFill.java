package com.coder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理
 */
public class CharEightFill {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = in.readLine()) != null){
            StringBuffer buffer = new StringBuffer(str);
            if(str.length() %8 != 0){
                for(int i= (8 - str.length() % 8); i>0; i--) buffer.append('0');
            }
            for(int i=0;i<buffer.length();i++){
                System.out.print(buffer.charAt(i));
                if((i+1)%8==0) System.out.println();
            }
        }
    }
}
