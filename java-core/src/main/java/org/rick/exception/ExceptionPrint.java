package org.rick.exception;

import org.junit.Test;

public class ExceptionPrint {
    @Test
    public void test1() {
        try {
            String str = null;
            str.getBytes();
        } catch (Throwable t) {
            Exception e = new Exception(String.format("a %s", "hello"), t);
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }
}
