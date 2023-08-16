package org.rick.copilot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用Github Copilot进行代码补全等操作
 */
public class CopilotDemo {
    public static void main(String[] args) {
        ListNode node = new CopilotDemo().new ListNode(1);
        node.next = new CopilotDemo().new ListNode(2);
        node.next.next = new CopilotDemo().new ListNode(3);
        node.next.next.next = new CopilotDemo().new ListNode(4);
        printList(node);
        reverse(node);
        printList(node);

        System.out.println(new CopilotDemo().calcBetweenTwoDays(new Date(), new Date()));

        Map<String, String> province = new HashMap<>();
        province.put("1", "北京");
        //键是省名，值是省简称
        province.put("北京", "京");
        province.put("天津", "津");
        province.put("山西", "晋");

        System.out.println(calc("10+2*3-4/2"));
        System.out.println(calc(1, 2, "*"));

        System.out.println(kmp("hellothisha", "this"));

    }
    
    //反转链表
    private static void reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static void printList(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    private int calcBetweenTwoDays(Date start, Date end) {
        return (int) ((end.getTime() - start.getTime()) / (1000 * 3600 * 24));
    }

    //a calculator supporting "+" "-" "*" "/"，should consider operators precedence
   private static int calc(int left, int right, String op) {
        switch (op) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            default:
                return left / right;
        }
    }

    private static int calc(String s) {
        int res = 0;
        int left = 0;
        int right = 0;
        String op = "+";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //如果是数字，就计算出这个数字
            if (Character.isDigit(c)) {
                right = right * 10 + (c - '0');
            }
            //如果是运算符，就计算出前面的结果
            if (!Character.isDigit(c) || i == s.length() - 1) {
                switch (op) {
                    case "+":
                        res += left;
                        left = right;
                        break;
                    case "-":
                        res += left;
                        left = -right;
                        break;
                    case "*":
                        left *= right;
                        break;
                    default:
                        left /= right;
                }
                op = String.valueOf(c);
                right = 0;
            }
        }
        res += left;
        return res;
   }

   //写个kmp算法
    private static int kmp(String s, String p) {
          int[] next = new int[p.length()];
          next[0] = -1;
          int i = 0;
          int j = -1;
          while (i < p.length() - 1) {
                if (j == -1 || p.charAt(i) == p.charAt(j)) {
                 i++;
                 j++;
                 next[i] = j;
                } else {
                 j = next[j];
                }
          }
          i = 0;
          j = 0;
          while (i < s.length() && j < p.length()) {
                if (j == -1 || s.charAt(i) == p.charAt(j)) {
                 i++;
                 j++;
                } else {
                 j = next[j];
                }
          }
          if (j == p.length()) {
                return i - j;
          } else {
                return -1;
          }
    }
}
