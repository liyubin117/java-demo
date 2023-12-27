package org.rick.throwable;

import org.junit.Test;

public class ThrowableTest {
    @Test
    public void testPrint() {
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

    @Test
    public void testCatchRuntimeException() {
        try {
            throw new RuntimeException("test throw runtime exception");
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }

    @Test
    public void testErrorCatch() {
        try {
            throw new Error("test throw error");
        }
//        catch (Exception e) {
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//            System.out.println(e);
//        }
        catch (Throwable t) {
            System.out.println(t.getCause());
            System.out.println(t.getMessage());
            System.out.println(t);
        } finally {
            System.out.println("finally executed");
        }
    }
}
