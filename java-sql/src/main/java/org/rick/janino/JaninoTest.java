package org.rick.janino;


import model.Event;
import org.codehaus.commons.compiler.IScriptEvaluator;
import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.ScriptEvaluator;

/**
 * Janino 是一个极小、极快的 开源Java 编译器（Janino is a super-small, super-fast Java™ compiler.）
 * Janino 不仅可以像 JAVAC 一样将 Java 源码文件编译为字节码文件，还可以编译内存中的 Java 表达式、块、类和源码文件，加载字节码并在 JVM 中直接执行。
 * Janino 同样可以用于静态代码分析和代码操作
 */
public class JaninoTest {

    public static void main(String[] args) {

        try {
            String content = "System.out.println(\"Hello world\");";
            IScriptEvaluator evaluator = new ScriptEvaluator();
            evaluator.cook(content);
            evaluator.evaluate(null);

            String express = "0 > 1";
            ExpressionEvaluator evaluator1 = new ExpressionEvaluator();
            evaluator1.cook(express);
            Object res = evaluator1.evaluate(null);
            System.out.println(express + " = " + res);

            IScriptEvaluator se = new ScriptEvaluator();
            se.setReturnType(Event.class);
            se.cook("import model.Event; "
                    + "Event o= new Event(\"1\",1000L,\"\"); "
                    + "return o; ");

            String express1 = "o.type != null && o.id.equals(\"hello janino\") ";
            ExpressionEvaluator evaluator2 = new ExpressionEvaluator();
            evaluator2.setExpressionType(Boolean.class);
            evaluator2.setParameters(new String[]{"o"}, new Class[]{Event.class});
            evaluator2.cook(express1);

            Event event = new Event("hello janino", 2L, "");
            Object res1 = evaluator2.evaluate(event);
            System.out.println(express1 + " = " + res1);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}