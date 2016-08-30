package Day06;
//方法
//方法用于封装一个特定的功能，定义时需要考虑五个要素：修饰词、返回值类型、方法名、参数列表、方法体
//在方法体中可通过return语句返回，return语句的作用在于结束方法且将数据返回给调用方。若方法定义的返回值类型为非void，则方法体中必须使用return返回，并且return后的表达式类型必须与方法定义的返回类型匹配

import Day07.Emp;

class Manager extends Emp {
	private double bonus;

	public Manager (String name,int age,double salary){
		super(name,age,salary);
	}
	public void setBonus(double bonus) {this.bonus = bonus; }
	public double getBonus() {return this.bonus;}
	public double getSalary() { return super.getSalary()+bonus; }
}



//使用final修改继承的类，可阻止进一步继承
final class Executive extends Manager {
	public Executive (String name,int age,double salary){
		super(name,age,salary);
	}
	public double getSalary() { return super.getSalary()+super.getBonus();}
}

public class Day06 {
	public static int sum(int a1,int a2){
		return a1+a2;
	}
	
	public static void say(String str){
		System.out.println(str);
	}	
	
	public static void main(String args[]){
		System.out.println(sum(3, 4));
		say("!");

		Emp [] emps = new Emp[4];
		Manager boss = new Manager("li",23,7000);
		boss.setBonus(1500);
		Executive ceo = new Executive("liyubin",28,100000);
		ceo.setBonus(1500);
		emps[0] = boss;
		emps[1] = new Emp("ha",22,5000);
		emps[2] = new Emp("ye",21,6000);
		emps[3] = ceo;

		for (Emp a:emps){
			System.out.println(a.getName()+" salary:"+a.getSalary());
		}

		//可对类进行强制类型转换，超类转换成子类时，若无法转换，会抛出ClassCastException异常，可以用instanceof运算符来检查
		if (emps[1] instanceof Manager) {
			Manager boss2 = (Manager) emps[1];
			System.out.println("emp[1]转换成功");
		}
		if (emps[0] instanceof Manager) {
			Manager boss2 = (Manager) emps[0];
			System.out.println("emp[0]转换成功");
		}

	}
}
