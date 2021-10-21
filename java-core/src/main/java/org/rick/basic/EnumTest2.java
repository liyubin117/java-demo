package org.rick.basic;

enum Week {
    MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(7);
	
	private int abbre;
	private Week(int abbre){
		this.abbre=abbre;
	}
	public int getAbbre(){
		return this.abbre;
	}
}

public class EnumTest2 {
    public static void main(String[] args) {
    	  for (Week e : Week.values()) {
              System.out.println(e.toString());
          }
    	
    	Week test = Week.TUE;
        
        //compareTo(E o)
        switch (test.compareTo(Week.MON)) {
        case -1:
            System.out.println("TUE 在 MON 之前");
            break;
        case 1:
            System.out.println("TUE 在 MON 之后");
            break;
        default:
            System.out.println("TUE 与 MON 在同一位置");
            break;
        }
         
        //getDeclaringClass()
        System.out.println("getDeclaringClass(): " + test.getDeclaringClass().getName());
         
        //name() 和  toString()
        System.out.println("name(): " + test.name());
        System.out.println("toString(): " + test.toString());
         
        //ordinal()， 返回值是从 0 开始
        System.out.println("ordinal(): " + test.ordinal());
        
        //返回内部值
        System.out.println("abbre: "+test.getAbbre());
    }
}