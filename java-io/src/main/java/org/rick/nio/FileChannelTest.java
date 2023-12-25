package org.rick.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        // 文件路径
        String filePath = "file/HelloWorld.java";
        Path path = Paths.get(filePath);

        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.APPEND)) {
            fileChannel.write(ByteBuffer.wrap("Hello NIO!".getBytes(StandardCharsets.UTF_8)));
        }


        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            // 创建一个 ByteBuffer 用于读取数据
            ByteBuffer buffer = ByteBuffer.allocate(1024); // 假设每次读取1024字节

            // 读取文件内容
            while (fileChannel.read(buffer) != -1) {
                // 准备读取
                buffer.flip();
                // 转换为字符串并打印
                System.out.println(StandardCharsets.UTF_8.decode(buffer));
                // 清空 ByteBuffer 以便再次使用
                buffer.clear();
            }
        }
    }
}
