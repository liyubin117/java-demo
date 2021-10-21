package org.rick.serial;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Student;
import model.PersonObjectWR;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//自定义Jackson库的序列化/反序列化过程
public class CustomJackson {

	public static void main(String[] args) throws IOException {
		//忽略字段
		D b=new D("li",26,9000,9999);
		System.out.println(b);
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		System.out.println(mapper.writeValueAsString(b));
		
		//引用同一个对象
		//默认序列化并反序列化后是不一样的
		Com com=new Com();
		com.name="com";
		C c=new C();
		c.first=c.second=com;
		String s=mapper.writeValueAsString(c);
		System.out.println("序列化后："+s);
		C c2=mapper.readValue(s, C.class);
		System.out.println("反序列化后："+c2);
		System.out.println("first与second是否一样："+(c2.first==c2.second));
		
		//循环引用
		Parent2 parent = new Parent2();
		parent.name = "老马";
		Child2 child = new Child2();
		child.name = "小马";
		parent.child = child;
		child.parent = parent;
		s=mapper.writeValueAsString(parent);
		System.out.println(s);
		//反序列化后也正常
		System.out.println(mapper.readValue(s, Parent2.class).child.parent.name);
		
		//反序列化时忽略未知字段
		File f=new File("file"+File.separator+"aperson.json");
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		PersonObjectWR p=mapper.readValue(f, PersonObjectWR.class);
		System.out.println(p);
		
		//继承和多态
		ShapeManager sm =  new ShapeManager();
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Circle(10));
		shapes.add(new Square(5));
		sm.setShapes(shapes);
		s=mapper.writeValueAsString(sm);
		System.out.println(s);
		sm=mapper.readValue(s, ShapeManager.class);
		System.out.println(Arrays.toString(sm.getShapes().toArray()));
		
		//修改字段名称
		Person2 p2=new Person2();
		p2.name="李宇彬";
		p2.age=26;
		s=mapper.writeValueAsString(p2);
		System.out.println(s);
		System.out.println(mapper.readValue(s, Person2.class));
	}

}

//@JsonIgnoreProperties可以指定忽略的字段列表
@JsonIgnoreProperties({"name","age"})
class D extends Student{
	
	//@JsonIgnore用在成员变量、getter、setter上是等效的，都可以忽略该字段
	//@JsonIgnore
	private double money;
	
	public double getMoney() {
		return money;
	}
	
	//@JsonIgnore
	public void setMoney(double money) {
		this.money = money;
	}

	public D(String name, int age, double score,double money){
		super(name, age, score);
		this.money=money;
	}

	@Override
	public String toString() {
		return "D [money=" + money + " Student=" + super.toString() +"]";
	}
}

//为方便使用，全部使用public修饰成员变量，仅作测试
//保持相同的引用关系。property="id"表示在序列化输出中新增一个属性"id"以表示对象的唯一标示，generator表示对象唯一ID的产生方法，这里是使用整数顺序数产生器IntSequenceGenerator
@JsonIdentityInfo(generator=IntSequenceGenerator.class,property="id")
class Com{
	public String name;
	@Override
	public String toString() {
		return "Com [name=" + name + "]";
	}
}

class C{
	public Com first;
	public Com second;
	@Override
	public String toString() {
		return "C [first=" + first + ", second=" + second + "]";
	}
}

//循环引用
class Parent2  {
    public String name;
    @JsonManagedReference
    public Child2 child;
}

class Child2 {
    public String name;
    @JsonBackReference
    public Parent2 parent;
}

//继承和多态
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Circle.class, name = "circle"),
    @JsonSubTypes.Type(value = Square.class, name = "square") })
class Shape {
}

@JsonRootName("圆")
class Circle extends Shape {
	private int r;	
    public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public Circle() {
		super();
	}
	public Circle(int r) {
		super();
		this.r = r;
	}
	@Override
	public String toString() {
		return "Circle [r=" + r + "]";
	}
}

//配置构造方法
class Square extends Shape {
    private int l;
    @JsonCreator
	public Square(@JsonProperty("l") int l) {
		super();
		
		this.l = l;
	}
//	public Square() {
//		super();
//	}
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}
	@Override
	public String toString() {
		return "Square [l=" + l + "]";
	}   	
}

class ShapeManager {
    private List<Shape> shapes;
	public List<Shape> getShapes() {
		return shapes;
	}
	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	@Override
	public String toString() {
		return "ShapeManager [shapes=" + shapes + "]";
	}	
}

//修改字段名称
@JsonRootName("人")
class Person2{
	@JsonProperty("姓名")
	public String name;
	@JsonProperty("年龄")
	public int age;
	@JsonProperty("当前日期")
	//格式化日期
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date date=new Date();
	@Override
	public String toString() {
		return "Person2 [name=" + name + ", age=" + age + "]";
	}
}
