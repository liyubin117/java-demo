package org.rick.coder;

// 题目二

import java.util.Stack;

/**
 * 394
 * 已知有一个字符串编码规则为: n(str)，表示括号内部的str重复n次。注意n大于0且小于10，str中密文的范围是所有的英文字母。但str会存在嵌套也就是说str还可能嵌套n(str)。
 * 例如：
 * 输入：s = "3(a2(c))3(a)2(bc)"
 * 输出："accaccaccaaabcbc"
 * 要求：请用两种方式（递归和非递归）实现该算法。
 */
public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decode("5(3(a2(c))3(a)2(bc))"));
        System.out.println(decode("3(ab)3(a)2(bc)"));
    }

    private static String decode( String s ){
        StringBuilder str = new StringBuilder();
        int num = 0;
        Stack<Integer> nums = new Stack<>();
        Stack<StringBuilder> strs = new Stack<>();

        for( char c : s.toCharArray() ){
            if( c == '(' ){
                nums.push( num );
                strs.push( str );

                num = 0;
                str = new StringBuilder();
            }
            else if( c == ')' ){
                int prenum = nums.pop();
                StringBuilder prestr = strs.pop();

                for( int i = 0; i < prenum; i++ ){
                    prestr.append( str );
                }
                str = prestr;
            }
            else if( c >= '0' && c <= '9' ){
                num = num * 10 + Integer.parseInt( String.valueOf( c ) );
            }
            else{
                str.append( c );
            }
        }
        return str.toString();
    }

}
