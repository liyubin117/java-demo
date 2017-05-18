package com.collectionmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CollectionsFuncDemo {

	public static void main(String[] args) {
		/**
		 * Collections的常用方法
		 */
		//查找和替换
		//二分查找
		List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{
		        35, 24, 13, 12, 8, 7, 1,24,8
		}));
		List<Integer> source = Arrays.asList(new Integer[]{
		        35, 24, 13, 12, 8, 24, 13, 7, 1
		});
		System.out.println("list:"+list);
		System.out.println("source:"+source);
		
		System.out.println(Collections.binarySearch(list, 7, Collections.reverseOrder()));
		//查找最大/小值
		System.out.println(Collections.max(list));
		System.out.println(Collections.min(list));
		//查找出现次数
		System.out.println(Collections.frequency(list, 24));
		//查找子list
		System.out.println(Collections.indexOfSubList(source, Arrays.asList(new Integer[]{24, 13})));
		System.out.println(Collections.lastIndexOfSubList(source, Arrays.asList(new Integer[]{24, 13})));
		//查看两个集合是否有交集，有交集返回false
		System.out.println(Collections.disjoint(list, source)?"无交集":"有交集");
		//替换
		Collections.replaceAll(list, 13, 88);
		System.out.println("13替换为88的list"+list);
		
		//排序和调整顺序
		//排序
		Collections.sort(list);
		System.out.println("排序后的list"+list);
		//交换元素位置
		Collections.swap(list, 1, 5);
		System.out.println("交换索引1和5后的list："+list);
		//翻转列表顺序
		Collections.reverse(list);
		System.out.println("翻转后的list："+list);
		//随机化重排
		//可重复，即设置随机种子
		Collections.shuffle(list, new Random(10));
		System.out.println("可重复的随机重排后的list："+list);
		//不可重复
		Collections.shuffle(list);
		System.out.println("随机重排后的list："+list);
		//循环移位，正数右移，负数左移
		Collections.rotate(list, 2);
		System.out.println("右移2的list："+list);
		Collections.rotate(list, -2);
		System.out.println("左移2的list："+list);
		
		//批量添加和修改
		Collections.addAll(list, source.toArray(new Integer[list.size()]));
		System.out.println("source加入到list后："+list);
		//批量填充固定值
		Collections.fill(list, 0);
		System.out.println("用0填充list："+list);
		//批量拷贝
		Collections.copy(list, source);
		System.out.println("拷贝source到list："+list);
	}

}
