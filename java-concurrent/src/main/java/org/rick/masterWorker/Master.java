package org.rick.masterWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
    //1.承装要执行的任务
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<>();
    //2.承装所有的worker对象，需要管控所有的worker启动和关闭
    private HashMap<String,Thread> workers = new HashMap<>();
    //3.承装每个worker执行的结果集
    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();

    //4.构造方法
    public Master(Worker worker,int workerCount){
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);
        for(int i=0;i<workerCount;i++){
            //key表示节点名，value表示线程执行对象
            workers.put("子节点"+Integer.toString(i), new Thread(worker));
        }
    }

    //5.提交任务
    public void submit(Task task){
        this.workQueue.add(task);
    }
    //6.执行任务（启动应用程序让所有的worker工作）
    public void execute(){
        for(Map.Entry<String,Thread> m : workers.entrySet()){
            Thread s = m.getValue();
            s.setName(m.getKey());
            s.start();
        }
    }
    //7.判断是否执行完毕
    public boolean isCompleted(){
        for(Map.Entry<String,Thread> m : workers.entrySet()){
            if(m.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }
    //8.归纳结果
    public int getResult(){
        int ret = 0;
        for(Map.Entry<String,Object> m : resultMap.entrySet()){
            ret += (int)m.getValue();
        }
        return ret;
    }


}
