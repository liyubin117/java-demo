package com.memoryctl;
import java.util.WeakHashMap;
 
/**
 * 弱引用映射WeakHashMap，键是弱引用对象
 * 使用场景： 
 * 能够节约存储空间，可用来缓存那些非必须存在的数据
 */
public class WeakHashMapDemo {
    public static void main(String[] args) throws InterruptedException{
       int size=100;
       Key[] keys=new Key[size];
       WeakHashMap<Key,Value> map=new WeakHashMap<Key,Value>();
       for(int i=0;i<size;i++){
    	   Key k=new Key(Integer.toString(i));
    	   Value v;
    	   //能被3整除的k强引用可达，不能被3整除的弱引用可达
           if(i%3==0){
               keys[i]=k;
               v=new Value("可被3整除");
           }else{      
               v=new Value(Integer.toString(i));
           }
           map.put(k, v);
       }
       System.gc();
       
       //发现最后保留的都是可被3整除的数据。因为强引用可达，不能被GC自动回收
       Thread.sleep(1000);
       System.out.println(map);
    }
}

class Element {
    private String ident;
 
    public Element(String id) {
       ident = id;
    }
 
    public String toString() {
       return ident;
    }
 
    public int hashCode() {
       return ident.hashCode();
    }
 
    public boolean equals(Object obj) {
       return obj instanceof Element && ident.equals(((Element) obj).ident);
    }
   
    protected void finalize(){
       System.out.println("Finalizing "+getClass().getSimpleName()+" "+ident);
    }
}
 
class Key extends Element{
    public Key(String id){
       super(id);
    }
}
 
class Value extends Element{
    public Value (String id){
       super(id);
    }
}