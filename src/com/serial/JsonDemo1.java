package com.serial;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pubtest.Person;

public class JsonDemo1 {

	public static void main(String[] args) throws JsonProcessingException {
		Person p1=new Person("liyubin","hz",25);
		ObjectMapper mapper=new ObjectMapper();
		//mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		//mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		String p=mapper.writeValueAsString(p1);
		System.out.println(p);
	}

}
