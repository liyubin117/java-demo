package org.rick;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级阻塞队列
 * 按Comparable接口比较值，从小到大排优先级
 */
public class PriorityBQueueDemo {
    static class Task implements Comparable<Task>{
        private int id;
        private String name;

        public Task(int id,String name){
            this.id=id;
            this.name=name;
        }

        @Override
        public int compareTo(Task o) {
            return (this.id>o.id) ? 1 : (this.id<o.id ? -1 : 0);
        }
        @Override
        public String toString(){
            return this.name;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();
        queue.add(new Task(10,"task 1: id 10"));
        queue.add(new Task(1,"task 2: id 1"));
        queue.add(new Task(20,"task 3: id 20"));
        System.out.println(queue);
        int size = queue.size();
        for(int i=0;i<size;i++){
            System.out.println(queue.take());
        }
    }
}
