package com.datageneration;

import java.util.Random;

public class BmUtil {
    /**
     * @param start 编号开头
     * @param len   编号长度
     * @param N     编号数字范围
     * @return 编码
     */
    public String getBm(String start, int len, int N) {
        Random random = new Random();
        String bmTmp = start;
        int l = len - start.length() + 1;
        for (int j = 1; j < l; j++) {
            bmTmp = bmTmp + random.nextInt(N);
        }
        return bmTmp;
    }
}