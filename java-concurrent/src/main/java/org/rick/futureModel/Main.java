package org.rick.futureModel;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FutureClient fc = new FutureClient();
        Data data = fc.request("请求参数");
        System.out.println("请求发送成功，可以做其他的事情...");
        System.out.println(Thread.currentThread().getName()+" "+System.currentTimeMillis());
        Thread.sleep(2000);

        //由打印出来的时间可以看出来，业务逻辑的执行时间由fc.request执行开始，即使主线程中断了2秒，但实际执行完成是1.5秒后
        String result = data.getRequest();
        System.out.println(result);
    }
}
