package com.json;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pubtest.Person;
import com.pubtest.Student;
import org.junit.Test;

public class JacksonDemo1 {

	public static void main(String[] args) throws IOException  {
		Person p1=new Person("liyubin","hz",26);
		Person p2=new Person("李宇彬","杭州",26);
		//ObjectMapper是一个线程安全的类，可以初始化并配置一次，被多个线程共享
		ObjectMapper mapper=new ObjectMapper();
		
		//格式化输出
		//mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		//mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		
		//序列化
		//序列化不受transient影响
		//输出到字符串
		String p=mapper.writeValueAsString(p1);
		System.out.println("输出到字符串：\n"+p);
		//输出到文件
		File f=new File("file"+File.separator+"person.json");
		mapper.writeValue(f, p1);
		//输出到字节流
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		System.out.println("输出到字节流：");
		mapper.writeValue(out, p2);
		System.out.println(out.toString());
		//输出到字符流
		f=new File("file"+File.separator+"person2.json");
		PrintWriter writer=new PrintWriter(f,"UTF-8");
		mapper.writeValue(writer, p2);
		
		//反序列化
		//以文件作为输入源
		Person p3=mapper.readValue(f, Person.class);
		System.out.println("反序列化：\n"+p3);
		
		
		//容器对象
		//序列化（与普通对象类似）
		f=new File("file"+File.separator+"students.json");
		ObjectMapper mapper2=new ObjectMapper();
		List<Student> students = Arrays.asList(new Student[] {
		        new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d) });
		mapper2 = new ObjectMapper();
		mapper2.enable(SerializationFeature.INDENT_OUTPUT);
		mapper2.writeValue(f, students);
		String str = mapper2.writeValueAsString(students);
		System.out.println(str);
		//反序列化（需要新建一个TypeReference匿名内部类对象来指定类型）
		mapper = new ObjectMapper();
		List<Student> list = mapper.readValue(f,
		        new TypeReference<List<Student>>() {});
		System.out.println(list.toString());
	}

	@Test
	public void test1() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		User user1 = new User(1, Arrays.asList("a","b"));
		System.out.println(mapper.writeValueAsString(user1));
	}

}
