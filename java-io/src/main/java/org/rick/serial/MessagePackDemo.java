package org.rick.serial;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.PersonObjectWR;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.File;
import java.io.IOException;

//只需向json的ObjectMapper内传入MessagePackFactory对象即可
public class MessagePackDemo {

	public static void main(String[] args) throws IOException, JsonMappingException {
		PersonObjectWR p2 = new PersonObjectWR("李宇彬", 30);
		File f=new File("file"+File.separator+"person.mp");
		ObjectMapper mapper=new ObjectMapper(new MessagePackFactory());
		
		//序列化到文件
		mapper.writeValue(f, p2);
		
		//以文件作为输入源
		PersonObjectWR p3=mapper.readValue(f, PersonObjectWR.class);
		System.out.println(p3);
		
	}

}
