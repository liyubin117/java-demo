package com.threads.futureModel;

public class FutureClient {
    public Data request(final String queryStr){
        //代理对象，先返回给客户端，告知其请求已经收到，其可以做其他的事情
        final FutureData futureData = new FutureData();
        //内部启动一个线程实际执行逻辑，此线程可以慢慢加载真实对象，然后传递给代理对象
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" "+System.currentTimeMillis());
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
                System.out.println(Thread.currentThread().getName()+" "+System.currentTimeMillis());
            }
        }.start();
        return futureData;
    }
}
