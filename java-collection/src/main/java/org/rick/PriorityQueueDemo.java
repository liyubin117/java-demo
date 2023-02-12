package org.rick;

import java.util.*;

/**
 * 优先级队列，原理是堆（完全二叉树中的最小堆以数组形式存储，节点索引i，父节点i/2，左孩子节点i*2-1，右孩子节点i*2+1）
 * 最小堆 new PriorityQueue()
 * 最大堆 new PriorityQueue(Collections.reverseOrder())
 */

public class PriorityQueueDemo {

	public static void main(String[] args) {
		//实现了Queue接口
		Queue<Integer> pq = new PriorityQueue<>();
		pq.offer(10);
		pq.add(22);
		pq.addAll(Arrays.asList(new Integer[]{
		    11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13 }));
		while(pq.peek()!=null){
		    System.out.print(pq.poll() + " ");
		}
		System.out.println();

        Queue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        maxQueue.offer(10);
        maxQueue.add(22);
        maxQueue.addAll(Arrays.asList(new Integer[]{
                11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13 }));
        while(maxQueue.peek()!=null){
            System.out.print(maxQueue.poll() + " ");
        }
        System.out.println();

        //模拟任务队列
		Queue<Task> tasks = new PriorityQueue<Task>(11, taskComparator);
		tasks.offer(new Task(20, "写日记"));
		tasks.offer(new Task(10, "看电视"));
		tasks.offer(new Task(100, "写代码"));

		Task task = tasks.poll();
		while(task!=null){
		    System.out.print("处理任务: "+task.getName()
		            +"，优先级:"+task.getPriority()+"\n");
		    task = tasks.poll();
		}
		
		//演示TopK
		TopK<Integer> top5 = new TopK<>(5);
		top5.addAll(Arrays.asList(new Integer[]{
		        100, 1, 2, 5, 6, 7, 34, 9, 3, 4, 5, 8, 23, 21, 90, 1, 0
		}));

		System.out.println(Arrays.toString(top5.toArray(new Integer[0])));
		System.out.println(top5.getKth());
		
		//演示中值
		Median<Integer> median = new Median<>();
		List<Integer> list = Arrays.asList(new Integer[]{
		        34, 90, 67, 45, 1, 4, 5, 6, 7, 9, 10
		});
		median.addAll(list);
		System.out.println(median.getM());
	}
	
	private static Comparator<Task> taskComparator = new Comparator<Task>() {

	    @Override
	    public int compare(Task o1, Task o2) {
	        if(o1.getPriority()>o2.getPriority()){
	            return -1;
	        }else if(o1.getPriority()<o2.getPriority()){
	            return 1;
	        }
	        return 0;
	    }
	};

	
	//静态内部类Task
	static class Task {
	    int priority;
	    String name;
	    
	    public Task(int priority, String name) {
	        this.priority = priority;
	        this.name = name;
	    }

	    public int getPriority() {
	        return priority;
	    }
	    
	    public String getName() {
	        return name;
	    }
	}
}

//应用一：TopK
class TopK <E> {
    private PriorityQueue<E> p;
    private int k;
    
    public TopK(int k){
        this.k = k;
        this.p = new PriorityQueue<>(k);
    }

    public void addAll(Collection<? extends E> c){
        for(E e : c){
            add(e);
        }
    }
    
    public void add(E e) {
        if(p.size()<k){
            p.add(e);
            return;
        }
        Comparable<? super E> head = (Comparable<? super E>)p.peek();
        if(head.compareTo(e)>0){
            //小于TopK中的最小值，不用变
            return;
        }
        //新元素替换掉原来的最小值成为Top K之一。
        p.poll();
        p.add(e);
    }
    
    public <T> T[] toArray(T[] a){
        return p.toArray(a);
    }

    public E getKth(){
        return p.peek();
    }
}

//应用二：中值
class Median <E> {
    private PriorityQueue<E> minP; // 最小堆
    private PriorityQueue<E> maxP; //最大堆
    private E m; //当前中值
    
    public Median(){
        this.minP = new PriorityQueue<>();
        this.maxP = new PriorityQueue<>(11, Collections.reverseOrder());
    }
    
    private int compare(E e, E m){
        Comparable<? super E> cmpr = (Comparable<? super E>)e;
        return cmpr.compareTo(m);
    }
    
    public void add(E e){
        if(m==null){ //第一个元素
            m = e;
            return;
        }
        if(compare(e, m)<=0){
            //小于中值, 加入最大堆
            maxP.add(e);
        }else{
            minP.add(e);
        }
        if(minP.size()-maxP.size()>=2){
            //最小堆元素个数多，即大于中值的数多
            //将m加入到最大堆中，然后将最小堆中的根移除赋给m
            maxP.add(this.m);
            this.m = minP.poll();
        }else if(maxP.size()-minP.size()>=2){
            minP.add(this.m);
            this.m = maxP.poll();
        }
    }
    
    public void addAll(Collection<? extends E> c){
        for(E e : c){
            add(e);
        }
    }
    
    public E getM() {
        return m;
    }
}