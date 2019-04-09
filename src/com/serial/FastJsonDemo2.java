package com.serial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.IOUtils;

import java.nio.charset.MalformedInputException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liyubin
 * @version 1.0
 * @company Netease
 * @description
 */
public class FastJsonDemo2 {
    public static void main(String[] args) {
        String jsonStr = "{\"width\":\"M (B)\",\"size\":\"7.5\"}";

        System.out.println("无序遍历结果（按hash值）：");
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("-------------------");
        System.out.println("有序遍历结果：");
        LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonStr, new TypeReference<LinkedHashMap<String, String>>() {});
        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        //com.alibaba.fastjson.JSONException: encodeUTF8 error
//        try {
//            IOUtils.encodeUTF8(new char[]{0xD845, 0x22}, 0, 2, new byte[]{});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        testRuntimeException();
    }

    public static void test() {
        throw new RuntimeException("encodeUTF8 error");
    }

    public static void testRuntimeException() {
        int a = 0;

        try {
            aaa333();
        } catch (Exception ex) {
            System.out.println("bbb");
        }

        System.out.print("abcdef");
    }

    private static void aaa333() {
        int a = 0;

        try {
            int b = 32 / a;
        } catch (Exception ex) {
            throw new RuntimeException("error;");
        }
    }
}
