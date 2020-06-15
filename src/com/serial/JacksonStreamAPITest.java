package com.serial;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * Jackson 流处理APi处理
 */
public class JacksonStreamAPITest {

    private JsonFactory factory;
    private JsonGenerator jsonGenerator;

    @Before
    public void init() throws Exception {
        factory = new JsonFactory();
        // 工厂全局设置
        factory.disable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES);
        // 设置解析器
        factory.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        // 设置生成器
        factory.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
        // 设置输出到console
        jsonGenerator = factory.createGenerator(System.out);
        // 覆盖上面的设置
        jsonGenerator.disable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
    }

    @Test
    public void testGenerator() throws Exception {
        String str = "hello,world!jackson!";
        // 输出字节
        jsonGenerator.writeBinary(str.getBytes());
        // 输出布尔型
        jsonGenerator.writeBoolean(true);
        // null
        jsonGenerator.writeNull();
        // 输出字符型
        jsonGenerator.writeNumber(2.2f);
        // 使用Raw方法会执行字符中的特殊字符
        jsonGenerator.writeRaw("\tc");
        // 输出换行符
        jsonGenerator.writeRaw("\r\n");

        // 构造Json数据
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("message", "Hello World!");
        jsonGenerator.writeArrayFieldStart("names");
        jsonGenerator.writeString("周杰伦");
        jsonGenerator.writeString("王力宏");
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    @Test
    public void testParser() throws Exception {
        String testStr = "{\"message\":\"Hello World!\",\"names\":[\"周杰伦\",\"王力宏\"]}";
        JsonParser p = factory.createParser(testStr);

        JsonToken t = p.nextToken();
        List<String> names = new ArrayList<String>();
        if ( t != JsonToken.START_OBJECT){
            System.out.println("Json格式不正确!");
        }
        while (t != JsonToken.END_OBJECT){
            if (t == JsonToken.FIELD_NAME && "message".equals(p.getCurrentName())){
                t =  p.nextToken();
                String message = p.getText();
                System.out.printf("My message to you is %s!\n", message);
            }
            if (t == JsonToken.FIELD_NAME && "names".equals(p.getCurrentName())){
                t = p.nextToken();
                while (t != JsonToken.END_ARRAY){
                    if (t == JsonToken.VALUE_STRING){
                        String name = p.getValueAsString();
                        names.add(name);
                    }
                    t = p.nextToken();
                }
            }
            t = p.nextToken();
        }
        System.out.println(names.toString());
        p.close();
    }

    @After
    public void close() throws Exception {
        jsonGenerator.close();
    }
}
