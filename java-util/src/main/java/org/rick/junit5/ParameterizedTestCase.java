package org.rick.junit5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ParameterizedTestCase {
    @ParameterizedTest
    @CsvSource({"li,32", "ha,10"})
    public void test1(String name, int age) {
        System.out.println(name + " " + age);
    }
}
