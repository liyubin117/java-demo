package org.rick.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;

public class JacksonTester {
   public static void main(String args[]) throws ParseException{ 
      String json = "{\"id\":1,\"theName\":\"Mark\"}"; 
      ObjectMapper mapper = new ObjectMapper();    
      try {
         Student student = mapper 
            .readerFor(Student.class) 
            .readValue(json); 
         System.out.println(student.rollNo +", " + student.name); 
      }
      catch (IOException e) { 
         e.printStackTrace(); 
      }
   }
}

class Student {
   public String name; 
   public int rollNo; 

   @JsonCreator 
   public Student(@JsonProperty("theName") String name, @JsonProperty("id") int rollNo){
      this.name = name; 
      this.rollNo = rollNo; 
   }
}