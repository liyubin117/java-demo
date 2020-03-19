package com.datageneration;

public class RandomNum {
    /**
     * 生成指定范围随机数
     *
     * @param begin
     * @param end
     * @return long类型随机数
     */
    public long getNum(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return getNum(begin, end);
        }
        return rtn;
    }

    /**
     * 生成指定范围随机数
     *
     * @param start
     * @param end
     * @return int类型随机数
     */
    public int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
}