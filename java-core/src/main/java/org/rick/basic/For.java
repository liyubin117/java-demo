package org.rick.basic;
import java.util.Arrays;

//对于for循环结构，一定要分析出需要解决业务的三个部分：
//循环变量初始状态
//循环条件
//循环变量的改变
public class For {
	public static void main(String[] args){
//		如果业务可以转换为“当……“这样的句式时，优先选择while语句来实现
		int m=10000;
		while(m<=12000){
			m+=m*(0.03);
		}
		System.out.println(m);
//	如果业务可转换为”直到……”这样的句式时，优先选择do-while语句来实现。
/*		Scanner console=new Scanner(System.in);
		
		String pwd;
		do{
			System.out.print("请输入密码:");
			pwd=console.next();
		}while(!"123456".equals(pwd));
		System.out.println("密码正确");
		*/
//		如果业务中可以获取到一个确切的循环次数时可以考虑使用for循环来实现

//程序即为：算法+数据结构
		//声明数组的语法为： 数据类型[] 数组名 = new 数据类型 [ 大小 ]
		//arr = {1,2,3,4,5};
		//基本类型 （数据元素为基本类型）的数组创建后，默认为其数组元素设置了初始值，元素的初始值如下所示：byte、short、char、int、long为0； float和double为0.0； boolean为false。
		int [] arr2 = { 10,23,30,-10,21 } ; 
		int [] arr3 = new int[]{ 3,6,8,9 };
		//调用数组的length属性可以获取数组的长度
		int len=arr3.length;
		System.out.println(len);
		
		//二维数组
		int [][] arr={{1,2,3,4,5},{12}};
		System.out.println(arr);
		System.out.println(arr[1]);
		System.out.println(arr[1].length);
		for (int a=0;a<arr[1].length;a++){
			System.out.println(arr[1][a]);
		}
		
		//遍历数组元组
		int [] arr4 = new int[10];
		for (int i=0;i<arr4.length;i++){
			arr4[i]=100+i;
		}
		//正序输出
		for (int i=0;i<arr4.length;i++){
			System.out.println(arr4[i]+"A");
			System.out.println(1+'A');
		}		
		//逆序输出
		for (int i=arr4.length-1;i>=0;i--){
			System.out.println(arr4[i]);
		}			
		
		int[ ] a = { 10 ,20 ,30 ,40 ,50 };
		int[ ] a1 = new int[ 6 ] ;
		//arraycopy用于数组复制
		System.arraycopy( a , 1 , a1 , 0 , 4 ); 
		for (int i=0;i<a1.length;i++){
			System.out.println(a1[i]);
		}			
		//java.utils.Arrays类的copyOf方法可实现数组的复制
		int [] b = {10,20,30,40,50};
		int [] b1 = Arrays.copyOf(b, 6);
		for (int i=0;i<b1.length;i++){
			System.out.println(b1[i]);
		}			
		//数组的“扩容”
		int [ ]  c = { 10,20,30,40,50 } ;
		c = Arrays . copyOf (  c, c.length+1 );
		for (int i=0;i<c.length;i++){
			System.out.println(c[i]);
		}
		//冒泡排序
		 int[] arr5 = {89,50,84,57,61,20,86};
		    for(int i=0;i<arr5.length-1;i++){
		        for(int j=0;j<arr5.length-1-i;j++){
		            if(arr5[j]>arr5[j+1]){
		                int temp = arr5[j];
		                arr5[j] = arr5[j+1];
		                arr5[j+1] = temp;
		            }
		        }
		    }
		    System.out.println("冒泡排序的结果为：");
			for (int i=0;i<arr5.length;i++){
				System.out.print(arr5[i]+" ");
			}   
		//Arrays.sort()封装了数组的排序算法
		int[ ] arr6 = { 49, 81, 1, 64, 77, 50, 0, 54, 77, 18 };
		Arrays.sort( arr6 ) ;
		System.out.println("\n使用Arrays.sort()实现升序排序的结果为：");
		for(int i=0; i<arr6.length; i++) {
		    System.out.print(arr6[i]+" " );
		}

	}
}
