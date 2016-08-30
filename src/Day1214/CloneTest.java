package Day1214;

/**
 * Created by Administrator on 2015-12-14.
 */
import Day07.Emp;

public class CloneTest {
    public static void main (String args[]){
        Emp original = new Emp ("Liyubin",23,10000);
        Emp copy = original;
        copy.raiseSalary(500);
        System.out.println(original.getSalary()+"  "+copy.getSalary());

        Emp original2 = new Emp ("Liyubin",23,10000);
        Emp clone = original2.clone();
        clone.raiseSalary(500);
        System.out.println(original2.getSalary()+"  "+clone.getSalary());
    }
}
