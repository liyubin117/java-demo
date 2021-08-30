package com.designmodel;

/**
 * @author Yubin Li
 * @date 2021/8/30 13:46
 * 监听者模式
 * 涉及到三个组件：事件源、事件监听器、事件对象，当事件源上发生操作时，它会调用事件监听器的一个方法，返回事件对象过来
 **/
public class ListenerDemo {
    public static void main(String[] args) {
        PersonSource person=new PersonSource();
        person.registerListener(new MyPersonListener());
        person.run();
        person.eat();
    }
}

//事件源
class PersonSource {
    //1.1首先定义一个私有的、空的监听器对象，用来接收传递进来的事件监听器(相当于一个全局变量)
    private PersonListener listener;
    //1.2将传递进来的事件监听器付给1.1中的listener
    public void registerListener(PersonListener personListener){
        this.listener=personListener;
    }
    //1.3判断listener是否为null，如果不为空，则执行监听器中的方法,否则照常调用
    public void run(){
        if(listener!=null){
            Event even=new Event(this);
            this.listener.dorun(even);
        }
        System.out.println("人具有跑的方法");
    }
    //1.4和1.3一个道理
    public void eat(){
        if(listener!=null){
            Event even=new Event(this);
            this.listener.doeat(even);
        }
        System.out.println("人具有吃的方法");
    }
}

//事件监听器
interface PersonListener{
    void dorun(Event even);
    void doeat(Event even);

}
//事件对象
class Event{
    private PersonSource person;

    public Event(PersonSource person) {
        super();
        this.person = person;
    }

    public Event() {
        super();
    }
    //事件对象 -> 事件源
    public PersonSource getPerson() {
        return person;
    }

    public void setPerson(PersonSource person) {
        this.person = person;
    }
}
//实现监听器接口中的方法
class MyPersonListener implements PersonListener{

    @Override
    public void dorun(Event even) {
//        PersonSource person = even.getPerson();//拿到事件源
        System.out.println("人在跑之前执行的动作");
    }

    @Override
    public void doeat(Event even) {
        System.out.println("人在吃之前执行的动作");
    }


}