package com.serial;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pubtest.ComplexStudent;
import com.pubtest.ContactInfo;
import com.pubtest.Person;
import com.pubtest.Student;

//只需将json的ObjectMapper替换为XmlMapper即可
public class XmlDemo {

	public static void main(String[] args) throws IOException {
		Person p2 = new Person("李宇彬", "杭州", 26);
		// ObjectMapper是一个线程安全的类，可以初始化并配置一次，被多个线程共享
		XmlMapper mapper = new XmlMapper();

		// 格式化输出
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		// 序列化
		// 序列化不受transient影响
		// 输出到文件
		File f = new File("file" + File.separator + "person.xml");
		mapper.writeValue(f, p2);
		
		//反序列化
		//以文件作为输入源
		Person p3=mapper.readValue(f, Person.class);
		System.out.println("反序列化：\n"+p3);
		
		//容器对象
		//序列化
		f = new File("file" + File.separator + "students.xml");
		Map<String, Student> map = new HashMap<String, Student>();
		map.put("zhangsan", new Student("张三", 18, 80.9d));
		map.put("lisi", new Student("李四", 17, 67.5d));
		String str = mapper.writeValueAsString(map);
		mapper.writeValue(f, map);
		System.out.println(str);
		//反序列化
		map=mapper.readValue(f, new TypeReference<Map<String,Student>>() {});
		System.out.println(map);
		
		//复杂对象
		//序列化
		f=new File("file" + File.separator + "cStu.xml");
		ComplexStudent student = new ComplexStudent("张三", 18);
		Map<String, Double> scoreMap = new HashMap<>();
		scoreMap.put("语文", 89d);
		scoreMap.put("数学", 83d);
		student.setScores(scoreMap);
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setPhone("18500308990");
		contactInfo.setEmail("zhangsan@sina.com");
		contactInfo.setAddress("中关村");
		student.setContactInfo(contactInfo);
		mapper.writeValue(f, student);
		//反序列化
		student=mapper.readValue(f, new TypeReference<ComplexStudent>() {});
		System.out.println(student);
	}

}
