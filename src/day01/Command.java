package day01;

/**
 * Created by Administrator on 2015-9-22.
 * 命令行参数
 * 程序名并不存储在args命令行参数中
 */
public class Command {
    public static void main(String[] args){
        if ( args[0].equals("-h") ){
            System.out.println("Hello world!");
        }
        else if ( args[0].equals("-g") ){
            System.out.println("Good bye!");
        }


        for (int i=1;i<args.length;i++){
            System.out.println(" "+args[i]);
        }
    }
}
