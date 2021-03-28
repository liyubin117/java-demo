package com.coder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 第一行接受一个由字母、数字和空格组成的字符串，第二行接受一个字母，然后输出输入字符串中该字母的出现次数。不区分大小写
 */
public class AppearCount {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars1 = br.readLine().toLowerCase().toCharArray();
        char[] chars2 = br.readLine().toLowerCase().toCharArray();
        int count = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[0]) {
                count++;
            }
        }
        System.out.println(count);
    }
}