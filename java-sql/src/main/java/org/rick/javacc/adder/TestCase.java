package org.rick.javacc.adder;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Yubin Li
 * @date 2021/12/15 19:56
 * javacc adder.jj
 **/
public class TestCase {
    @Test
    public void testLex() {
        InputStream is = new ByteArrayInputStream("11.2 + 2.3 + 4".getBytes());
        SimpleCharStream scs = new SimpleCharStream(is);
        AdderTokenManager tokenManager = new AdderTokenManager(scs);
        Token token;
        while (true) {
            token = tokenManager.getNextToken();
            if (token == null || token.kind == AdderConstants.EOF)
                break;
            System.out.println(token.image);
        }
    }

    @Test
    public void testSyntax() throws ParseException {
        InputStream is = new ByteArrayInputStream("11.22 + 2 + 4".getBytes());
        Adder parser = new Adder(is);
        double result = parser.evaluate();
        System.out.println(result);
    }
}
