package PackageTest;
import static java.lang.System.*;
import static com.pubtest.Calc.*;

import com.pubtest.*;
/**
 * This program demonstrates the use of packages.
 * @version 1.11 2004-02-19
 * @author Cay Horstmann
 */
public class PackageTest
{
    public static void main(String[] args)
    {
        // because of the import statement, we don't have to use com.horstmann.corejava.Employee here
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);

        harry.raiseSalary(5);

        // because of the static import statement, we don't have to use System.out here
        out.println("name=" + harry.getName() + ",salary=" + harry.getSalary());
        
        //静态导入后，其中的静态方式可直接使用
        System.out.println(add(8,2));
        //System.out.println(substract(8,2));
    }
}
