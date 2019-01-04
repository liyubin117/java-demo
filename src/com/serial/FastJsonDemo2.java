package com.serial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

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
        LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonStr, new TypeReference<LinkedHashMap<String, String>>() {
        });
        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
