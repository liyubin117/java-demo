package org.rick.coder.test;

import org.junit.Test;

import java.util.Stack;

public class StackQueueTest {
    @Test
    public void test394() {
        decodeString("3[a]2[bc]");
    }
    //leetcode 394 字符串解码 middle
    private static String decodeString(String s) {
        StringBuilder res = new StringBuilder(); //收集字符
        Stack<Integer> nums = new Stack<>(); //存储每个[]前的重复次数
        Stack<StringBuilder> strs = new Stack<>(); //进入[]前存储已收集的串
        
        int num = 0;
        char[] chars = s.toCharArray();
        
        for(char c : chars){
            if(c >= '0' && c <= '9'){ //记录重复次数，次数可能>10
                num = num * 10 + c -'0';
            }
            else if((c >= 'a' && c <= 'z') ||(c >= 'A' && c <= 'Z')){
                res.append(c);
            } 
            else if (c == '[') {
                strs.push(res); //把之前收集的字符串存入栈
                res = new StringBuilder();
                nums.push(num); //接下来的重复次数存入栈
                num = 0;
            }
            else if(c == ']'){ //清算这个[]内的串
                int times = nums.pop();
                for(int i = 0; i < times; i++){
                    strs.peek().append(res); //上一级字符串后面增加这个[]内的串
                }
                res = strs.pop();
                
            }
        }
        return res.toString();
    }
}