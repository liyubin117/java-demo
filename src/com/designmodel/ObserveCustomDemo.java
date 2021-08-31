package com.designmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yubin Li
 * @date 2021/8/31 16:51
 **/
public class ObserveCustomDemo {
    public static void main(String[] args) {
        WechatServer server = new WechatServer();

        Observer userZhang = new Subscriber("ZhangSan");
        Observer userLi = new Subscriber("LiSi");
        Observer userWang = new Subscriber("WangWu");

        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);

        server.setInfomation("Hello!");

    }
}

/**
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 */
interface Observerable {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();
}

/***
 * 抽象观察者
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 */
interface Observer {
    public void update(String message);
}

/**
 * 被观察者，也就是微信公众号服务
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 */
class WechatServer implements Observerable {

    //注意到这个List集合的泛型参数为Observer接口，设计原则：面向接口编程而不是面向实现编程
    private List<Observer> list;
    private String message;

    public WechatServer() {
        list = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {

        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if(!list.isEmpty())
            list.remove(o);
    }

    //遍历
    @Override
    public void notifyObserver() {
        for(int i = 0; i < list.size(); i++) {
            Observer oserver = list.get(i);
            oserver.update(message);
        }
    }
    public void setInfomation(String s) {
        this.message = s;
        System.out.println("微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}

/**
 * 观察者
 * 实现了update方法
 */
class Subscriber implements Observer {

    private String name;
    private String message;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + " 收到推送消息： " + message);
    }

}