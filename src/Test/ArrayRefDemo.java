package Test;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-2-5.
 */
public class ArrayRefDemo {
    public static void main(String[] args) {
        int[] s = {1, 3, 120, 8, 83,24,56,78}; //静态初始化数组
        fun(s);
        print(s);

        System.out.println();
        ArraySort.sort(s);
        ArraySort.print(s);
        //OR
        System.out.println();
        int[] s2 = {1, 7, 120, 8, 83,24,56,78};
        java.util.Arrays.sort(s2);
        ArraySort.print(s2);

        //copy
        System.out.println();
        int[] s3 = {1,2,3,4,5};
        int[] d = {11,22,33,44,55};
        ArrayCopy.copy(s3, 2, d, 3, 3);
        print(d);
        //OR
        System.out.println();
        int[] s4 = {1,2,3,4,5};
        int[] d2 = {11,22,33,44,55};
        System.arraycopy(s4,2,d2,3,2);
        print(d2);

        System.out.println();
        for (int i=1;i<10;i++){
            for(int j=1;j<i+1;j++){
                System.out.print(i+"*"+j+"="+(i*j)+"\t");
            }
            System.out.println();
        }

    }
        public static void fun(int[] x){
            x[1]=7;
        }
        public static void print(int[] a){
            for(int i=0;i<a.length;i++){
                System.out.print(a[i]+" ");
            }
        }
}


class ArraySort{
    public static void sort(int[] temp){
        for (int i=1;i<temp.length;i++){
            for(int j=0;j<temp.length;j++){
                if(temp[i]<temp[j]){
                    int x = temp[i];
                    temp[i] = temp[j];
                    temp[j] = x;
                }
            }
        }
    }
    public static void print(int[] temp){
        for (int i=0;i<temp.length;i++){
            System.out.print(temp[i]+"\t");
        }
    }
}

class ArrayCopy{
    public static void copy(int[] s,int a,int[] d,int b,int length){
        if((a+length)>(s.length)){
            System.out.println("out of range!");
        }else{
            for(int i=0;i<length;i++){
                d[b+i] = s[a+i];
            }
        }

    }
}