package org.rick.coder;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Yubin Li
 * 合法的括号，空串也算
 */
public class LegalParentheses {
    public static void main(String[] args) {
        Arrays.asList(determine("")
                ,determine("(hello)")
                ,determine(")hello(")
                ,determine("({hi]haha[})")).forEach(System.out::println);
    }

    private static boolean determine(String input) {
        if (input.equals("")) return true;
        Stack<Character> stack = new Stack<>();
        for (Character c : input.toCharArray()) {
            if (c.equals('{') || c.equals('[') || c.equals('(')) {
                stack.push(c);
            } else if (c.equals('}') || c.equals(']') || c.equals(')')) {
                if (stack.isEmpty()) return false;
                Character temp = stack.pop();
                if (temp.equals('{') && !c.equals('}')) return false;
                if (temp.equals('[') && !c.equals(']')) return false;
                if (temp.equals('(') && !c.equals(')')) return false;
            }
        }
        return stack.isEmpty();
    }
}
