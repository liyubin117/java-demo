package org.rick.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class FastJsonDemo {
    private static String str = "{\"mobilePhone\":\"13510398031\",\"userName\":\"李四\"}";
    private static String str2 = "[{\"mobilePhone\":\"13510398031\",\"userName\":\"李四\"},{\"mobilePhone\":\"13824568145\",\"userName\":\"张三\"}]";
    private static String str3 = "{\"userName\":\"李四\",\"mobilePhone\":\"13510398031\"}";
    private static String str4 = "{\"mobilePhone\":\"13510398031\",\"userName\":\"李四\"}";
    private static String str5 = "{\"log_id\":\"Chat\",\"server\":\"1004\",\"role_id\":\"33951326\",\"origin_json\":\"{\\\"y_account_id\\\":\\\"\\\",\\\"server\\\":1004,\\\"log_id\\\":\\\"Chat\\\",\\\"old_accountid\\\":\\\"aebfwvls2u3taspl@ad.netease.win.163.com\\\",\\\"chat_time\\\":1532880279,\\\"y_obj\\\":\\\"\\\",\\\"channel\\\":5,\\\"axis\\\":\\\"(0,0,0)\\\",\\\"log_ts\\\":\\\"2019-01-03 10:43:13\\\",\\\"content\\\":\\\"我改属性了\\\",\\\"y_level\\\":50,\\\"scene\\\":\\\"ChatScene\\\",\\\"role_name\\\":\\\"杰超丶\\\",\\\"account_id\\\":\\\"aebfwvls2u3taspl@ad.netease.win.163.com\\\",\\\"role_id\\\":33951326,\\\"mac_addr\\\":\\\"08:00:27:8B:EF:5F\\\",\\\"y_name\\\":\\\"\\\",\\\"udid\\\":\\\"e6acd77f09b89d67\\\",\\\"log_ds\\\":\\\"2019-01-03\\\"}\",\"ds\":\"2019-01-03\",\"ts\":\"2019-01-03 10:43:13\"}";


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

        object = new JSONObject(true);
        object.put("k1","v1");
        object.put("k2","v2");
        System.out.println(object.toJSONString());

        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.config(SerializerFeature.SortField, false);
        serializer.write(object);
        System.out.println(out.toString());

        JSONObject obj = new JSONObject(true).parseObject("{\"k2\":\"v2\",\"k1\":\"v1\"}");
        JSONObject obj2 = new JSONObject(true).parseObject("{\"k1\":\"v1\",\"k2\":\"v2\"}");
        System.out.println(obj.equals(obj2));
        System.out.println(JSONObject.toJSONString(obj));
        System.out.println(JSONObject.toJSONString(obj2));


        SerializeWriter out2 = new SerializeWriter();
        JSONSerializer serializer2 = new JSONSerializer(out2);
        serializer2.config(SerializerFeature.SortField, false);
        serializer2.write(obj);
        System.out.println(out2.toString());

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


        //对于内容相同，键序不同的json，toJSONString会按相同键序输出成字符串
        System.out.println(JSONObject.parseObject(str4).toJSONString());
        System.out.println(JSONObject.parseObject(str3).toJSONString());
        System.out.println(JSONObject.parseObject(str5).getString("origin_json"));
        String str6 = "{\"server\":1004,\"old_accountid\":\"aebfwvls2u3taspl@ad.netease.win.163.com\",\"log_id\":\"Chat\",\"y_account_id\":\"\",\"chat_time\":1532880279,\"y_obj\":\"\",\"channel\":5,\"axis\":\"(0,0,0)\",\"log_ts\":\"2019-01-03 10:43:13\",\"content\":\"我改属性了\",\"y_level\":50,\"scene\":\"ChatScene\",\"role_name\":\"杰超丶\",\"account_id\":\"aebfwvls2u3taspl@ad.netease.win.163.com\",\"role_id\":33951326,\"mac_addr\":\"08:00:27:8B:EF:5F\",\"y_name\":\"\",\"udid\":\"e6acd77f09b89d67\",\"log_ds\":\"2019-01-03\"}";
        String str7 = "{\"y_account_id\":\"\",\"server\":1004,\"log_id\":\"Chat\",\"old_accountid\":\"aebfwvls2u3taspl@ad.netease.win.163.com\",\"chat_time\":1532880279,\"y_obj\":\"\",\"channel\":5,\"axis\":\"(0,0,0)\",\"log_ts\":\"2019-01-03 10:43:13\",\"content\":\"我改属性了\",\"y_level\":50,\"scene\":\"ChatScene\",\"role_name\":\"杰超丶\",\"account_id\":\"aebfwvls2u3taspl@ad.netease.win.163.com\",\"role_id\":33951326,\"mac_addr\":\"08:00:27:8B:EF:5F\",\"y_name\":\"\",\"log_ds\":\"2019-01-03\",\"udid\":\"e6acd77f09b89d67\"}";
        System.out.println(JSONObject.parseObject(str6).toJSONString().equals(JSONObject.parseObject(str7).toJSONString()));
        System.out.println(JSONObject.parseObject(str6).toString().equals(JSONObject.parseObject(str7).toString()));
        System.out.println(JSONObject.parseObject(str6).toString());
        System.out.println(JSONObject.parseObject(str7).toString());
        System.out.println(JSONObject.parseObject(str6).toJSONString());
        System.out.println(JSONObject.parseObject(str7).toJSONString());


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