package com.serial;

import java.io.File;
import java.io.IOException;

import org.msgpack.jackson.dataformat.MessagePackFactory;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pubtest.Person;
//只需向json的ObjectMapper内传入MessagePackFactory对象即可
public class MessagePackDemo {

	public static void main(String[] args) throws IOException, JsonMappingException {
		Person p2 = new Person("李宇彬", "杭州", 26);
		File f=new File("file"+File.separator+"person.mp");
		ObjectMapper mapper=new ObjectMapper(new MessagePackFactory());
		
		//序列化到文件
		mapper.writeValue(f, p2);
		
		//以文件作为输入源
		Person p3=mapper.readValue(f, Person.class);
		System.out.println(p3);
		
	}

}
