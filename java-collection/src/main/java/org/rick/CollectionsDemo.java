package org.rick;

import java.util.*;

public class CollectionsDemo {

	public static void main(String[] args) {
		/**
		 * 适配器：将一种类型的接口转换成另一种类型的接口
		 */
		//空容器方法
		//返回静态不可变空容器，节省创建新对象的内存和时间开销
		//用于构建返回值
		List<String> list = Collections.emptyList();
		Map<String, Integer> map = Collections.emptyMap();
		Set<Integer> set = Collections.emptySet();
		//方法返回的空容器只能读取，不能写入元素，否则会报UnsupportedOperationException异常
		try{
			list.add("a");
		}catch(UnsupportedOperationException e){
			System.out.println("空容器不能用于写入，只能用于读取");
			e.printStackTrace();
			System.out.println();
		}

		//单一对象方法：将单例对象转换为容器。只能读取，不能写入
		//用于构建返回值
		Collection<String> coll = Collections.singleton("编程");
		Set<String> set1 = Collections.singleton("编程");
		List<String> list1 = Collections.singletonList("老马");
		Map<String, String> map1 = Collections.singletonMap("老马", "编程");
		//用于构建参数
		List<String> list2 = new ArrayList<>();
		Collections.addAll(list2, "a", "b", "c", "d", "b");
		list2.removeAll(Collections.singleton("b"));
		System.out.println(list2);
		
		/**
		 * 装饰器：接受一个接口对象，并返回一个同样接口的对象，
		 * 不过，新对象可能会扩展一些新的方法或属性，扩展的方法或属性就是所谓的"装饰"，
		 * 也可能会对原有的接口方法做一些修改，达到一定的"装饰"目的
		 * Collections的装饰器方法只改变了原有接口方法的性质
		 */
		//写安全：unmodifiableXXX方法就是使容器对象变为只读的，写入会抛出UnsupportedOperationException异常
		mainMethod();
		
		//类型安全：checkedXXX方法确保容器中不会保存错误类型的对象。与老代码互动时，在泛型机制失灵（JDK1.5前，无泛型）的情况下确保类型的正确性
		//老版本代码无泛型，本例希望插入Integer类型
		List list3 = new ArrayList();
		list3 = Collections.checkedList(list3, Integer.class);
		try{
			list3.add("hello");
		}catch(ClassCastException e){
			System.out.println("checkedXXX方法确保容器中不会保存错误类型的对象");
			e.printStackTrace();
			System.out.println();
		}
		
		//线程安全
		//非线程安全：ArrayList、LinkedList、HashSet、TreeSet、LinkedHashSet、EnumSet、HashMap、TreeMap、LinkedHashMap、EnumMap、PriorityQueue、ArrayDeque
		//synchronizedXxx保证线程安全
	}
	//第三方提供的方法
	public static void thirdMethod(Collection<String> c){
	    c.add("bad");
	}
	//本方法，不希望第三方方法修改容器
	public static void mainMethod(){
	    List<String> list = new ArrayList<>(Arrays.asList(
	            new String[]{"a", "b", "c", "d"}));
	    //不用unmodifiableCollection装饰器方法，本容器会改变
	    thirdMethod(list);
	    System.out.println(list);
	    //用unmodifiableCollection装饰器方法，会抛出异常，防止容器被修改
	    try{
		    thirdMethod(Collections.unmodifiableCollection(list));
	    }catch(UnsupportedOperationException e){
	    	System.out.println("unmodifiableXxx装饰器方法保证写安全");
	    	e.printStackTrace();
	    	System.out.println();
	    }
	}
}

