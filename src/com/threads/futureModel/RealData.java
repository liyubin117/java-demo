package com.threads.futureModel;
/**
 * 实际业务逻辑
 */
public class RealData implements Data {
    private String result;

    public RealData(String queryStr){
        System.out.println("根据"+queryStr+"进行查询，比较耗时...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完毕，获取结果");
        this.result = "结果是100";
    }

    @Override
    public String getRequest() {
        return this.result;
    }
}
