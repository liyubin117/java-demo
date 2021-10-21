package org.rick.futureModel;
/**
 * 包装类，里层是RealData
 */
public class FutureData implements Data{
    private RealData realData;
    private boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        //如果set已完成，则直接返回
        if(isReady) return;
        //如果set未完成，装载真实对象后唤醒等待线程
        this.realData = realData;
        this.isReady = true;
        notify();
    }

    @Override
    public synchronized String getRequest() {
        //如果set未完成，则等待
        while(!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果set已完成，则返回结果
        return this.realData.getRequest();
    }
}
