package com.lyb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author liyubin
 * @version 1.0
 * @company Netease
 * @description TODO
 */
public class Test2 {
    public static void main(String[] args) {
        String schemaStrArray = "[\n" +
                "{\n" +
                "\"log_id\":\"test\"\n" +
                ",\"body\":{    \n" +
                " \"name\": \"test\", \n" +
                " \"type\": \"record\",\n" +
                " \"fields\": [\n" +
                "   {\"name\": \"id\", \"type\": \"int\"},\n" +
                "   {\"name\": \"name\", \"type\": \"string\"}\n" +
                "]}\n" +
                "}\n" +
                ",\n" +
                "{\n" +
                "\"log_id\":\"test2\"\n" +
                ",\"body\":{    \n" +
                " \"name\": \"test2\", \n" +
                " \"type\": \"record\",\n" +
                " \"fields\": [\n" +
                "   {\"name\": \"id\", \"type\": \"int\"},\n" +
                "   {\"name\": \"addr\", \"type\": \"string\"}\n" +
                "]}\n" +
                "}\n" +
                "]";
        System.out.println(schemaStrArray);
        JSONArray schemaJsonArray = JSONObject.parseArray(schemaStrArray);
        Map<String,String> schemaMap = new HashMap<>();
        for(int i=0;i<schemaJsonArray.size();i++){
            JSONObject obj = schemaJsonArray.getJSONObject(i);
            schemaMap.put(obj.getString("log_id"),obj.getString("body"));
            System.out.println(obj.getString("body"));
        }

        String str = "conf/local/ball/ball_kafka2hdfs.conf";
        System.out.println(str.substring(0,str.lastIndexOf("/")+1));
        System.out.println(str.replace(".conf",".schema"));

        System.out.println("test2/ds=2019-02-23/1/2/3_===_logid".split("_===_")[0]);

        System.out.println(
                JSONObject.parseObject("{\"ds\":\"2019-02-23\",\"log_id\":\"test2\",\"origin_json\":\"{\\\"id\\\":4,\\\"addr\\\":\\\"hangzhou\\\",\\\"key\\\":\\\"value\\\"}\"}").getString("log_id")
        );

        System.out.println("0".equals(0));

        System.out.println("-------------------------");
        JSONObject obj = JSONObject.parseObject("{\"es_schema\":\"luoge-dev.qn_up_child\",\"log_schema\":\"com.netease.fuxi.luoge.nsh.model.tables.v1.ChannelChat\",\"relation\":\"{\\\"msg123\\\":[\\\"msg\\\",\\\"extra_info\\\"],\\\"role_id\\\":[\\\"role_id1\\\"],\\\"role_level\\\":[\\\"role_level\\\"]}\"}");
        System.out.println(obj.getString("es_schema"));
        System.out.println(obj.getString("log_schema"));
        System.out.println(obj.getString("relation"));
        JSONArray arr = JSONObject.parseObject(obj.getString("relation")).getJSONArray("msg123");
        for(int i=0;i<arr.size();i++){
            System.out.println(arr.get(i));
        }

        JSONObject message = JSONObject.parseObject("{\"role_id1\":5,\"msg\":\"我是\",\"role_level\":59,\"extra_info\":{\"age\":\"20\",\"name\":\"XiaoMing\"},\"role_gender\":0,\"friends_id\":[1,2,3,4,5,6],\"is_robot\":0,\"schema\":\"com.netease.fuxi.luoge.nsh.model.tables.v1.ChannelChat\",\"ts\":\"2019-08-05 15:11:39\"}");

        System.out.println("-------------------------");

        Map<String, String> map = new HashMap<>();
        map.put("com.netease.fuxi.luoge.lihe.model.tables.v1.Lihetest","a");
        map.put("com.netease.fuxi.luoge.lihe.model.tables.v3.Lihetest","b");
        map.put("com.netease.fuxi.luoge.lihe.model.tables.v20.Lihetest","c");
        map.put("com.netease.fuxi.luoge.lihe.model.tables.v20.ab","abcde");
        map.put("com.netease.fuxi.luoge.lihe.model.tables.v12.ab","abcd");
        System.out.println(map);
        Map<String, String> result = new HashMap<>();
        for(Map.Entry entry:map.entrySet()){
            result.put(getFormalSchema(entry.getKey().toString()),entry.getValue().toString());
        }
        System.out.println(result);

        System.out.println(getSchemaVersion("com.netease.fuxi.luoge.lihe.model.tables.v23423423424212.ab"));

        map.put("a","value");
        map.put("a","newvalue");
        System.out.println(map.get("a"));
        System.out.println(map.get("b")==null);

        for(int i=0;i<=3;i++){
            System.out.println(i);
            if(i==1){
                break;
            }
            System.out.println("afasdf");
        }


    }

    private static String getFormalSchema(String schema){
        return schema.substring(schema.indexOf(".",schema.indexOf(".")+1)+1, schema.indexOf("model.tables")-1).replace(".","/")+ "/" +schema.replace(schema.substring(0, schema.lastIndexOf(".")+1), "");
    }

    private static String getSchemaVersion(String schema){
        String str = schema.substring(schema.indexOf("model.tables.v")+14);
        return str.substring(0,str.lastIndexOf("."));
    }
}
