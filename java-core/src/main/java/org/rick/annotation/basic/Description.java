package org.rick.annotation.basic;

import java.lang.annotation.*;

//适用于方法、类、接口
@Target({ElementType.METHOD,ElementType.TYPE})
//生命周期为运行时
@Retention(RetentionPolicy.RUNTIME)
//允许子类继承父类的类注解(但子类不能继承接口上的注解)
@Inherited
//允许写入java doc
@Documented
public @interface Description {
	//成员。无参无异常，变量类型为：基本类型、引用类型(String、Class、Annotation、Enumeration)
	//可以指定默认值
	//若注解只有一个成员，则必须命名为value()，使用时不用写成员名和=
	String desc();
	String author();
	int age() default 18;
}
