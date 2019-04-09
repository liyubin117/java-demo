package com.useful;
import java.util.regex.Pattern;
import java.util.regex.Matcher;;

class Regex{
	private Pattern p;
	private Matcher m;
	public Regex(String reg,String s){
		this.p=Pattern.compile(reg);
		this.m=p.matcher(s);
	}
	public Regex(String reg){
		this.p=Pattern.compile(reg);
	}
	
	public boolean getResult(){
		return m.matches();
	}
	public String replace(String s){
		return m.replaceAll(s);
	}
	public String[] getSplit(String s){
		return p.split(s);
	}
}

public class RegexDemo1 {
	public static void main(String[] args){
		Regex r=new Regex("[0-9]+","123890");
		if(r.getResult()){
			System.out.println("由数字组成");
		}else{
			System.out.println("不全是数字");
		}
		
		r=new Regex("\\d{4}-\\d{2}-\\d{2}","1988-09-10");
		if(r.getResult()){
			System.out.println("日期格式符合要求");
		}else{
			System.out.println("日期格式不符合要求");
		}
		
		r=new Regex("\\d","12a89b");
		System.out.println(r.replace("_"));
		
		r=new Regex("\\d+");
		for(String s:r.getSplit("abc123def456")){
			System.out.print(s+"\t");
		}
		System.out.println();
		
		//使用String类的正则
		String s="aa123bb456cc";
		if(s.matches("\\d")){
			System.out.println("是数字");
		}
		System.out.println(s.replaceAll("\\d","_"));
		String[] ss=s.split("\\d+");
		for(int i=0;i<ss.length;i++){
			System.out.print(ss[i]+"\t");
		}
        System.out.println();

		String regexStr = "^.*#.*#.*#.*$";
		String str = "10001#2018-07-31 16:05:39#000#GameEnd";
		Matcher matcher = Pattern.compile(regexStr).matcher(str);
        System.out.println(matcher.groupCount());
		for(int i=0;i<matcher.groupCount();i++){
            System.out.println(matcher.group(i));
        }

        Pattern p=Pattern.compile("([a-z]+)(\\d+)");
        Matcher m=p.matcher("aaa2223bb");
        System.out.println(m.find());   //匹配aaa2223
        System.out.println(m.groupCount());   //返回2,因为有2组
        System.out.println(m.start(1));   //返回0 返回第一组匹配到的子字符串在字符串中的索引号
        System.out.println(m.start(2));   //返回3
        System.out.println(m.end(1));   //返回3 返回第一组匹配到的子字符串的最后一个字符在字符串中的索引位置.
        System.out.println(m.end(2));   //返回7
        System.out.println(m.group(1));   //返回aaa,返回第一组匹配到的子字符串
        System.out.println(m.group(2));   //返回2223,返回第二组匹配到的子字符串

		regexStr = "^lyb_test_%s_format_.*$";

		Pattern p1 = Pattern.compile("^CREATE EXTERNAL TABLE");
        System.out.println(p1.matcher("CREATE EXTERNAL TABLE T1").find());
        System.out.println("server string,".split(" ")[0]);

    }
}
