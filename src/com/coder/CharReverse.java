package com.coder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharReverse {
    public static void main(String[] args) throws Exception {
        Method2.invoke();
    }

    static class Method1{
        public static void invoke() throws Exception{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            char[] arr = in.readLine().toCharArray();
            int size = arr.length;
            char[] result = new char[size];
            for(int i=0; i<size; i++){
                result[size-1-i] = arr[i];
            }
            System.out.println(result);
        }
    }
    // 直接按倒序输出，不使用中间数组浪费空间
    static class Method2{
        public static void invoke() throws Exception{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            char[] arr = in.readLine().toCharArray();
            int size = arr.length;
            for(int i=size-1; i>=0; i--){
                System.out.print(arr[i]);
            }
            System.out.println();
        }
    }
}

