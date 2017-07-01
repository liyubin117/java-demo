package com.algorithm;

/**
 * BF算法
 * 用于匹配，简单但低效
 */
public class BFMatch<E> {
	public static void main(String[] args) {
		Integer[] s={1,2,3,4,5,9,0,3,4,5,8,2,3};
		//{1,2,3} {1,2} {1,3} {2,3} {3}
		Integer[] t={2,3};
		BFMatch<Integer> match=new BFMatch<>();
		System.out.println(match.bfmatch(s,t));
	}
	
	//返回s中t的起始位置
	public int bfmatch(E[] s,E[] t){
		int position=0;
		for(int i=0,j=0;i<s.length;i++){
			if(j<t.length){
				//若相等则下标都加1
				if(s[i].equals(t[j])){
					//当回溯的时候，记下i此时的位置作为position
					if(j==0){
						position=i;
					}
					
					j++;
				}else{
				//若不相等则只s下标加1，t下标重置，position置-1
					j=0;
					position=-1;	
				}
				//当比完t中的元素后即返回结果
				if(j==t.length){
					return position;
				}
			}
			
		}
		//-1表示不匹配
		return -1;
	}
}
