package org.rick.basic;

/**
 * Created by Administrator on 2015-8-29.
 * 很多时候，为了使用的方便，可以对一个类定义多个构造方法，这些构造方法都有相同的名称（类名），只是方法的参数不同，称之为构造方法的重载
 */
 class Gouzaocz {
    int row;
    int col;
    String str;
    public Gouzaocz(){}
    public Gouzaocz(int row,int col){
        this.row = row;
        this.col = col;
    }
    public Gouzaocz(int row,String str){
        this.row = row;
        this.str = str;

    }
    public void printcz(){
        System.out.println("row:"+this.row+" str:"+this.col);
        System.out.println("row:"+this.row+" str:"+this.str);
    }
}

public class ConstructorOverload {
    public static void main(String args[]){
        Gouzaocz cz = new Gouzaocz();
        cz.printcz();
        System.out.println("-------------------------");
        Gouzaocz cz2 = new Gouzaocz(3,4);
        cz2.printcz();
        System.out.println("-------------------------");
        Gouzaocz cz3 = new Gouzaocz(3,"hello");
        cz3.printcz();
    }
}
