package org.rick.coder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 */
public class WordReverse {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str = in.readLine().split(" ");
        for(int i=str.length-1;i>0;i--){
            System.out.print(str[i] + " ");
        }
        System.out.println(str[0]);
    }
}
