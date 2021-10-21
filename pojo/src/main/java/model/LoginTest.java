package model;

/**
 * Created by Administrator on 2015-12-24.
 */
public class LoginTest {
    public static void main(String[] args){
        Operate o1 = new Operate(args);
        o1.fun();
    }
}

class Test{
    private String name="liyubin";
    private String password="12345678";

/*    public void set(String name,String password){
        this.name=name;
        this.password=password;
    }*/

    public boolean result(String name,String password){
        if(this.name.equals(name)&&this.password.equals(password)){
            return true;
        }else{
            return false;
        }
    }
}

class Operate extends Test{
    String[] args;

    public Operate(String[] args){
        this.args=args;
    }
    public void fun(){
        if(this.args.length!=2){
            System.out.println("wrong nums!");
            System.exit(1);
        }else if( result(this.args[0],this.args[1]) ){
            System.out.println("correct account");
        }else{
            System.out.println("wrong account");
        }
    }

}
