package model;

public class Calc {
	public static int add(int t,int d){
		return t+d;
	}
	
	public int substract(int t,int d){
		return t-d;
	}
	
	public static <T> T addT(T t,T d) throws Exception{
		if(t instanceof Integer&&d instanceof Integer){
			Integer tmp=(Integer)t+(Integer)d;
			return (T)tmp;
		}else if(t instanceof String&&d instanceof String){
			String tmp=(String)t+(String)d;
			return (T)tmp;
		}else{
			throw new Exception("not Integer or String,cant be cast");
		}
	}
}
