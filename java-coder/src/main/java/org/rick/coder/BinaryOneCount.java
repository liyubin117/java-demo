package org.rick.coder;

import java.io.InputStream;

/**
 输入整数的二进制形式，1的个数
 */
public class BinaryOneCount {
    public static void main(String[] args) throws Exception {
        // 使用Integer.toBinaryString把输入的整数转换为二进制字符串，然后迭代判断
        InputStream stream = System.in;
        int l ;
        byte[] bytes = new byte[1024];
        while ((l = stream.read(bytes)) > 0) {
            String numStr = new String(bytes, 0, l - 1);
            int num = Integer.parseInt(numStr);
            char [] numChars = Integer.toBinaryString(num).toCharArray();
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
