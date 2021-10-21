package org.rick.io;


import model.Student;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentDB {
	
	private BasicDB db;
	
	public StudentDB(String path,String dbName){
		try {
			db = new BasicDB(path, dbName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static byte[] toBytes(Student student) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(bout);
		dout.writeUTF(student.getName());
		dout.writeInt(student.getAge());
		dout.writeDouble(student.getScore());
		return bout.toByteArray();
	}

	public void saveStudents(Map<String, Student> students)
			throws IOException {
		for (Map.Entry<String, Student> kv : students.entrySet()) {
			db.put(kv.getKey(), toBytes(kv.getValue()));
		}
	}
	
	public byte[] getStudent(String key) throws IOException{
		return db.get(key);
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		StudentDB sdb=new StudentDB("./file/dir/","students");
		Student stu=new Student("李",26,100);
		Student stu2=new Student("叶",24,99);
		Map<String,Student> stus=new HashMap<>();
		stus.put("1", stu);
		stus.put("2", stu2);
		sdb.saveStudents(stus);
		
		byte[] bytes=sdb.getStudent("1");
		System.out.println(bytes.length);
		System.out.println(new String(bytes,0,5,"UTF-8"));
		System.out.println((bytes[8]));
		
	}

}

