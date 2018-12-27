package com.serial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class FastJsonDemo {
    private static String str = "{\"mobilePhone\":\"13510398031\",\"userName\":\"李四\"}";
    private static String str2 = "[{\"mobilePhone\":\"13510398031\",\"userName\":\"李四\"},{\"mobilePhone\":\"13824568145\",\"userName\":\"张三\"}]";

    public static void main(String[] args) {
        //JSONObject转JSONStr
        JSONObject object = new JSONObject();
        object.put("age", 20);
        object.put("name", "xiaoming");
        object.remove("name");
        replaceJSONKey(object,"age","new_age");
        System.out.println(object.getString("new_age"));
        String jsonStr = JSON.toJSONString(object);
        System.out.println(jsonStr);

        //JSONStr转JSONObject
        JSONObject jsonObj = JSON.parseObject(str);
        System.out.println(jsonObj.get("userName"));

        //JSONStr转JavaBean
        Human human = JSON.parseObject(jsonStr,Human.class);
        System.out.println(human);

        //JavaBean转JSONStr
        System.out.println(JSON.toJSONString(human));

        //JavaBean转JSONObject
        System.out.println(JSON.toJSON(human));

        String str = "[{\"key\":\"^$\"},{\"name\":\"\\\\liyubin\"}]";
        List<Pattern> regexList = new LinkedList<>();
        JSONArray regexArr = JSONObject.parseArray(str);
        JSONObject jo = regexArr.getJSONObject(1);
        System.out.println(jo.get("name"));
//        for(Object regexStr : regexArr){
//            regexList.add( Pattern.compile((String)regexStr.get("key")) );
//        }
//        for(Pattern pattern : regexList){
//            System.out.println(pattern);
//        }
    }

    public static void replaceJSONKey(JSONObject obj,String oldKey,String newKey){
        if(obj.containsKey(oldKey)){
            obj.put(newKey, obj.get(oldKey));
            obj.remove(oldKey);
        }
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