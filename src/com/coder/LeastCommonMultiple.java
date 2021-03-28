package com.coder;


import java.io.*;

public class LeastCommonMultiple {
    public static void main(String[] args) throws Exception {
        Method2.invoke();
    }

    // 先找出两者最大值，然后迭代判断是否满足，否则加1
    static class Method1 {
        private static boolean isRight(int result, int a, int b) {
            return result % a == 0 && result % b == 0;
        }

        public static void invoke() throws Exception {
            String in = new BufferedReader(new InputStreamReader(System.in)).readLine();
            String[] arr = in.split(" ");
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);
            int result = a > b ? a : b;

            while (!isRight(result, a, b)) {
                result++;
            }
            System.out.println(result);
        }
    }
    // 先确定大小数，然后用大数余小数，小数置为大数，余数置为小数，继续迭代相余直到小数为0，然后用初始大小数相乘之积除以最终的大数
    static class Method2 {
        public static void invoke() throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str;
            while ((str = br.readLine()) != null) {
                String[] strArr = str.split(" ");
                int n = Integer.parseInt(strArr[0]);
                int m = Integer.parseInt(strArr[1]);
                int j = m * n;
                // 确定m是大数，n是小数
                if (n > m) {
                    int temp = m;
                    m = n;
                    n = temp;
                }

                while (n != 0) {
                    int r = m % n;
                    m = n;
                    n = r;
                    System.out.printf("m:%d\tn:%d\n",m,n);
                }
                int max = j / m;
                System.out.println(max);
            }
        }
    }
}