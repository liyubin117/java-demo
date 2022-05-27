package org.rick.junit5;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class TempDirDemo {
    @TempDir
    Path tempDir;

    @Test
    public void testWrite() throws IOException {
        Path numbers = tempDir.resolve("numbers.txt");
        System.out.println(numbers.getParent());
        System.out.println(numbers);

        List<String> lines = Arrays.asList("1", "2", "3");
        Files.write(numbers, lines);

        assertAll(
                () -> assertTrue("File should exist", Files.exists(numbers)),
                () -> assertLinesMatch(lines, Files.readAllLines(numbers)));
    }
}
