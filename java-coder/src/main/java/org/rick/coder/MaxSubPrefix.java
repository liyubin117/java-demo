package org.rick.coder;

import static org.junit.Assert.assertEquals;

/**
 * 最长公共前缀
 */
public class MaxSubPrefix {
    public static void main(String[] args) {
        assertEquals("fi", get(new String[]{"first", "fight", "field"}));
        assertEquals("", get(new String[]{"first", "fight", "gear"}));
    }

    private static String get(String[] strs) {
        if (strs.length == 0) return "";
        String result = strs[0];
        if (strs.length == 1) return result;
        for (String str : strs) {
            int j = 0;
            for (; j < result.length() && j < str.length(); j++) {
                if (result.charAt(j) != str.charAt(j)) break;
            }
            result = result.substring(0, j);
            if (result.equals("")) return result;
        }
        return result;
    }
}
