package com.lyb;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.util.ArrayUtil;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCase {

    @Test
    public void test1(){
        String str = "jdbc:presto://fuxi-luoge-76:7988;user=hive;";
        System.out.println(str.replace(";","?").substring(0,str.length()-1));
    }

    @Test
    public void test2(){
        String str = "impala::ball_ods_kudu.ods_itembuy_123";
        System.out.println(str.substring(str.indexOf("::")+2,str.lastIndexOf(".")));

        System.out.println(str.contains("::") && str.contains("."));

    }

    @Test
    public void test3(){
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList("a","b"));
        set.forEach(x -> {
            Set<String> tmp = map.getOrDefault("key",new HashSet<>());
            tmp.add(x);
            map.put("key", tmp);
        });
        System.out.println(map);
    }

    @Test
    public void test4()
    {
//        JSONArray arr = JSONArray.parseArray("[\"select_on_index_nsh_template3\",\"all_on_cluster_luoge-dev_index_template_yq_alarm\"]");
//        String regStr = "(.*)_on_cluster_(.*)_index_(.*)";
//        Set set = new HashSet();
//        arr.stream()
//                .filter(x -> Pattern.compile(regStr).matcher(x.toString()).matches())
//                .forEach(x -> {
//                    Matcher matcher = Pattern.compile(regStr).matcher(x.toString());
//                    System.out.println(matcher.matches());
//                    System.out.println(matcher.groupCount());
//                    System.out.println(matcher.group(1));
//                    System.out.println(matcher.group(2));
//                    System.out.println(matcher.group(3));
//                    set.add(matcher.group(3));
//                });
//        set.add(1);
//        System.out.println(set);

        String regStr = "es\\.(.*)_on_cluster_(.*)_index_(.*)";
        JSONArray templateArr = JSONArray.parseArray(
                "[\"es.select_on_index_nsh_template3\",\"es.all_on_cluster_luoge-dev_index_template_yq_alarm\"]"
                // todo:
//                    getResultFromRestful("http://10.241.12.67:2333/api/v1" + "/web/users/" + user + "/permissions?type=es", token).second
        );
        Set templateSet = new HashSet<String>();
        templateArr.stream()
                .filter(x -> Pattern.compile(regStr).matcher(x.toString()).matches())
                .forEach(x -> {
                    System.out.println(x);
                    Matcher matcher = Pattern.compile(regStr).matcher(x.toString());
                    System.out.println(matcher.matches());
                    System.out.println(matcher.groupCount());
                    System.out.println(matcher.group(0));
                    System.out.println(matcher.group(3));

//                        String str = JSONObject.parseObject(getResultFromRestful("http://fuxi-luoge-76:36002/_template/"+template, "").second)
//                                .getString(template);
//                        JSONObject.parseObject(str).getJSONArray("index_patterns")
//                                .forEach(y -> templateSet.add(y));


                });

        System.out.println(Pattern.compile("yq_alarm*".replace("*",".*")).matcher("yq_alarm_total").matches());

        System.out.println("es.1.2.3".replace("es.",""));
    }

    @Test
    public void test() {
        System.out.println(
                "set 'source_topic.properties.group.id' = 'new';\n" +
                        "set 'source_topic.scan.startup.mode' = 'latest-offset';\n" +
                        "\n" +
                        "CREATE TABLE sink (\n" +
                        "    item_id VARCHAR,\n" +
                        "    item_type varchar\n" +
                        ") WITH (\n" +
                        "    'connector' = 'redis',\n" +
                        "    'host' = '10.122.173.131:6379',\n" +
                        "    'port' = '6388',\n" +
                        "    'mode' = 'single',\n" +
                        "    'db.index' = '0',\n" +
                        "    'password' = 'sloth1234',\n" +
                        "    'lookup.cache.type' = 'all',\n" +
                        "    'lookup.cache.ttl' = '3000',\n" +
                        "    'primary.key' = 'item_id',\n" +
                        "    'connector.command' = 'SET'\n" +
                        ");\n" +
                        "\n" +
                        "INSERT INTO sink\n" +
                        "select\n" +
                        "  item_id,\n" +
                        "  item_type\n" +
                        "from\n" +
                        "  ht_poc.source_topic;"
        );
    }
}
