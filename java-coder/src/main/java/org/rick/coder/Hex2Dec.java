package org.rick.coder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Hex2Dec {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = in.readLine()) != null){
            char[] arr = str.substring(2).toLowerCase().toCharArray();
            int result = 0;
            for(int i=0; i<arr.length; i++){
                result += hexMap(arr[i]) * Math.pow(16, arr.length-1-i);
            }
            System.out.println(result);
        }

    }
    private static int hexMap(char c){
        switch (c){
            case 'a': return 10;
            case 'b': return 11;
            case 'c': return 12;
            case 'd': return 13;
            case 'e': return 14;
            case 'f': return 15;
            default: return Integer.parseInt(String.valueOf(c));
        }
    }
}
