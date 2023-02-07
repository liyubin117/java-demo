package org.rick.coder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 输入整数，输出二进制形式的值及1的个数
 */
public class BinaryOneCount {
    public static void main(String[] args) throws Exception {
        // 使用Integer.toBinaryString把输入的整数转换为二进制字符串，然后迭代判断
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = reader.readLine()) != null) {
            char [] numChars = Integer.toBinaryString(Integer.parseInt(str)).toCharArray();
            int countNum = 0;
            for (int i = 0;i < numChars.length; i++){
                if (numChars[i] == '1'){
                    countNum = countNum + 1;
                }
            }
            System.out.println(new String(numChars) + "\t" + countNum);
        }
    }
}
