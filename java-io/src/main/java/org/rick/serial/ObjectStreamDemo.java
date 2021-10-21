package org.rick.serial;

import model.Student;

import java.io.*;

//实现了Serializable接口的类可以被ObjectOutputStream/ObjectInputStream（字节流的一部分）读写，是Java自带的序列化/反序列化方法
public class ObjectStreamDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		//使用对象流自动序列化和反序列化
		
		//简单对象Student
		//Student的age是transient修饰的，会在自动序列化时被忽略
		File f=new File("file"+File.separator+"object.txt");
		ObjectOutputStream out=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		Student stu=new Student("liyubin",26,9999);
		out.writeObject(stu);
		out.close();
		
		ObjectInputStream in=new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		Student stu2=(Student)in.readObject();
		System.out.println(stu2);
		in.close();
		
		//复杂对象：a对象和b对象都共同引用了c对象，序列化并反序列化后，仍指向同一个对象
		Common c = new Common("common");
		A a = new A("a", c);
		B b = new B("b", c);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		out = new ObjectOutputStream(bout);
		out.writeObject(a);
		out.writeObject(b);
		out.close();

		in = new ObjectInputStream(
		        new ByteArrayInputStream(bout.toByteArray()));
		A a2 = (A) in.readObject();
		B b2 = (B) in.readObject();

		if (a2.getCommon() == b2.getCommon()) {
		    System.out.println("reference the same object");
		} else {
		    System.out.println("reference different objects");
		}
		
		//复杂对象：循环引用，序列化并反序列化后，仍能保持原有的关系
		Parent parent = new Parent("老马");
		Child child = new Child("小马");
		parent.setChild(child);
		child.setParent(parent);
		
		bout = new ByteArrayOutputStream();
		out = new ObjectOutputStream(bout);
		out.writeObject(parent);
		out.writeObject(child);
		out.close();

		in = new ObjectInputStream(new ByteArrayInputStream(
		        bout.toByteArray()));
		parent = (Parent) in.readObject();
		child = (Child) in.readObject();

		if (parent.getChild() == child && child.getParent() == parent
		        && parent.getChild().getParent() == parent
		        && child.getParent().getChild() == child) {
		    System.out.println("reference OK");
		} else {
		    System.out.println("wrong reference");
		} 
	
	
	}

}
