package org.rick;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by liyubin on 2018/5/31 0031.
 */
public class LinkedBlockingDequeueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Integer> bd = new LinkedBlockingDeque(1000);  //当设置的最大容量比要插入的元素少时，put插入后程序不会停止,offer插入后到达最大容量会自动停止
        for(int i=0;i<10000;i++){
            bd.offer(i);
        }
        System.out.println(bd.size());
        for(int i=0;i<10;i++){
            System.out.println(bd.take());
        }
        System.out.println(bd.size());  //take()取出元素后size变少

    }
}
