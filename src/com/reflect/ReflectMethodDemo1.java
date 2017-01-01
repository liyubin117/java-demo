package com.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//反射调用方法
public class ReflectMethodDemo1 {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class<?> c = Class.forName("com.reflect.Person");
		// 调用无参方法
		Method m = c.getMethod("sayChina");
		m.invoke(c.newInstance());
		// 调用有参方法
		m = c.getMethod("sayHello", String.class, int.class);
		System.out.println((String) m.invoke(c.newInstance(), "李宇彬", 25));

		// setter、getter类型的方法用到了反射调用
		Object obj = c.newInstance();
		setter(obj, "name", "李宇彬", String.class); // 调用setter方法
		setter(obj, "age", 25, int.class); // 调用setter方法
		System.out.print("姓名：");
		getter(obj, "name");
		System.out.print("年龄：");
		getter(obj, "age");
	}

	/**
	 * Object obj：要操作的对象 String att：要操作的属性 Object value：要设置的属性内容 Class<?>
	 * type：要设置的属性类型
	 */
	public static void setter(Object obj, String att, Object value, Class<?> type) {
		try {
			Method met = obj.getClass().getMethod("set" + initStr(att), type); // 得到setter方法
			met.invoke(obj, value); // 设置setter的内容
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getter(Object obj, String att) {
		try {
			Method met = obj.getClass().getMethod("get" + initStr(att)); // 得到setter方法
			System.out.println(met.invoke(obj)); // 调用getter取得内容
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String initStr(String old) { // 将单词的首字母大写
		String str = old.substring(0, 1).toUpperCase() + old.substring(1);
		return str;
	}
}
