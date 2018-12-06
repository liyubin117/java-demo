package com.serial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;

import java.io.IOException;

public class FastJsonDemo {
    private static String jsonStr = "{\"mobilePhone\":\"13510398031\",\"userName\":\"李四\"}";
    private static String jsonStr2 = "[{\"mobilePhone\":\"13510398031\",\"userName\":\"李四\"},{\"mobilePhone\":\"13824568145\",\"userName\":\"张三\"}]";

    public static void main(String[] args) {
        //JSONObject转JSONStr
        JSONObject object = new JSONObject();
        object.put("age", 20);
        object.put("name", "xiaoming");
        String jsonStr = JSON.toJSONString(object);
        System.out.println(jsonStr);

        //JSONStr转JSONObject
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        System.out.println(jsonObj);

        //JSONStr转JavaBean
        Human human = JSON.parseObject(jsonStr,Human.class);
        System.out.println(human);

        //JavaBean转JSONStr
        System.out.println(JSON.toJSONString(human));

        //JavaBean转JSONObject
        System.out.println(JSON.toJSON(human));
    }

}

class Human {
    private int age;
    private String name;

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString(){
        return this.name+"+"+this.age;
    }
}