package org.rick.coder;

import java.io.InputStream;

/**
 * 字符串最后一个单词的长度，单词以空格分开
 */
public class LastWordLength {
    public static void main(String[] args) throws Exception{
        int times = 0;
        InputStream in = System.in;
        char c = (char) in.read();
        while (c != '\n') {
            if (c == ' ') {
                times = 0;
            } else {
                times += 1;
            }
            c = (char) in.read();
        }
        System.out.println(times);
    }
}
