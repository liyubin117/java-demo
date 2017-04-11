package com.collectionmap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * @author liyubin
 * Arrays类:包含一些对数组操作的静态方法
 */
public class ArraysDemo {

	public static void main(String[] args) {
		//打印toString
		int[] arr = {9,8,3,4};
		System.out.println(Arrays.toString(arr));
		String[] strArr = {"hello", "world"};
		System.out.println(Arrays.toString(strArr));

		//数组排序sort
		/**
		 * 基本类型排序
		 */
		//全部排序
		int[] iArr = {4, 9, 3, 6, 10};
		Arrays.sort(iArr);
		System.out.println("基本类型全部排序:"+Arrays.toString(iArr));
		//区间排序，包括fromIndex位置的元素，不包括toIndex位置的元素
		int[]iArr2 = {4, 9, 3, 6, 10};
		Arrays.sort(iArr2,0,3);
		System.out.println("基本类型区间排序:"+Arrays.toString(iArr2));
		/**
		 * 对象类型排序
		 * 对象需要实现Comparable接口才能排序
		 */
		String[] sArr = {"hello","world", "Break","abc","abC","abc123"};
		Arrays.sort(sArr);
		System.out.println("对象类型全部排序:"+Arrays.toString(sArr));
		String[] sArr2 = {"hello","world", "Break","abc","abC","abc123"};
		//自定义排序，实现Comparator接口
		Arrays.sort(sArr2, String.CASE_INSENSITIVE_ORDER);
		System.out.println("对象类型不分大小写排序:"+Arrays.toString(sArr2));
		String[] sArr3 = {"hello","world", "Break","abc","abC","abc123"};
		Arrays.sort(sArr3, new Comparator<String>() {
		    @Override
		    public int compare(String o1, String o2) {
		        return o2.compareToIgnoreCase(o1);
		    }
		});
		System.out.println("String类型不分大小写从大到小排序:"+Arrays.toString(sArr3));
		//Collections类中有两个静态方法，可以返回逆序的Comparator
		String[] sArr4 = {"hello","world", "Break","abc","abC","abc123"};
		Arrays.sort(sArr4, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
		System.out.println("String类型不分大小写从大到小排序(使用Collections.reverseOrder方法):"+Arrays.toString(sArr4));
		
		/**
		 * 二分查询（先sort）
		 * 与sort类似，能对基本类型、对象类型，还可自定义比较器
		 */
		int[] a = {3,7,5,13,21,18};
		Arrays.sort(a);
		//返回值是有讲究的：若能找到，返回的索引是相对于排序前的；若找不到，返回的索引是-(插入点+1)，插入点是插入后可保持数组有序的位置
		System.out.println("二分查询21:"+Arrays.binarySearch(a, 21));
		System.out.println("二分查询6:"+Arrays.binarySearch(a, 6));
		
		/**
		 * 数组拷贝
		 */
		String[] b = {"hello","li","yu","bin"};
		//若复制长度大于原数组长度，则自动补上默认值
		String[] newArr = Arrays.copyOf(b, 5);
		System.out.println("数组拷贝:"+Arrays.toString(newArr));
		//区间包括from，不包括to
		System.out.println("数组区间拷贝:"+Arrays.toString(Arrays.copyOfRange(b, 1, 3)));
		
		/**
		 * 数组相等性比较
		 * 支持基本类型、对象类型
		 */
		String[] c=new String[]{"hello","li","yu"};
		System.out.println("数组相等性比较:"+Arrays.equals(b, c));
		
		/**
		 * 填充值
		 * 给数组中的每个元素或给定区间设置一个相同的值
		 */
		int[] d = {3,5,7,13,21};
		//区间包括from，不包括to
		Arrays.fill(d,2,4,0);
		System.out.println("区间填充值:"+Arrays.toString(d));
		Arrays.fill(d,0);
		System.out.println("整体填充值:"+Arrays.toString(d));
		
		/**
		 * 哈希值
		 */
		System.out.println("哈希值:"+Arrays.hashCode(c));
		
		/**
		 * 多维数组
		 * 除了第一维的长度需要指定外，其他维的长度不需要指定，甚至，第一维中，每个元素的第二维的长度可以不一样
		 * 多维数组只是一个假象，只有一维数组，只是数组中的每个元素还可以是一个数组
		 * Arrays.deepXxx方法进行操作
		 */
		int[][] arrs = new int[][]{
	        {0,1},
	        {2,3,4},
	        {5,6,7,8}
		};
		System.out.println("多维数组打印:"+Arrays.deepToString(arrs));
	}

}
