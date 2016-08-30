package day01;

import java.util.Arrays;

/**
 * Created by Administrator on 2015-9-22.
 * 数组
 */
public class Array1 {

    public static void main(String[] args){
        int [] a = new int[10];
        for (int m=0;m<a.length;m++){
            a[m] = m;
        }
        //最简单的打印整个数组内容的方式，不过有特定格式
        System.out.println(Arrays.toString(a));
        //for each循环，不用考虑下标越界的问题
        for (int i: a) {
            System.out.print(i);
        }
        //创建数组的同时，赋予初始值
        int [] b = {1,2,7,8,9,10};
        System.out.println("\nb:"+Arrays.toString(b));
        //数组拷贝
        //两数组变量指向同一个数组
        int [] c = b;
        c[1]=100;
        System.out.println("b:"+Arrays.toString(b));
        //将一个数组的所有值拷贝到另一个数组中，这两个数组只是值相同，但指向的地址不同
        int [] d = Arrays.copyOf(b,b.length);
        System.out.println("d:"+Arrays.toString(d));
        if (d != b){
            System.out.println("b和d指向的地址不同");
        }

        int [] e = {1,5,453,32,1231,1234,123,12320,7,9,0,30};
        System.out.println("e排序前：" + Arrays.toString(e));
        Arrays.sort(e);
        System.out.println("e排序后："+Arrays.toString(e));

        //规则数组
        final int NMAX = 10;
        int[][] odds = new int[NMAX+1][];
        for (int n=0;n<=NMAX;n++){
            odds[n] = new int[n+1];
        }
        for (int i=0;i<odds.length;i++){
            for (int j=0;j<odds[i].length;j++){
                odds[i][j] = i+j;
            }
        }

 /*       int[] f = {0,0,1,2,3,4,5,6,7,8,9,10};
        for (int i:f){
            System.out.print(i+"\t");
        }*/

        for (int i=0;i<odds.length;i++){
            for (int j=0;j<odds[i].length;j++){
/*                if(j==0){
                    System.out.print(odds[i][j]+"\t");
                }*/
                System.out.print(odds[i][j] + "\t");
            }
            System.out.println();
        }

    }

}
