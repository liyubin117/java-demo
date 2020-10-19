package com.useful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.regex.Pattern;
import java.util.regex.Matcher;;

class Regex {
    private Pattern p;
    private Matcher m;

    public Regex(String reg, String s) {
        this.p = Pattern.compile(reg);
        this.m = p.matcher(s);
    }

    public Regex(String reg) {
        this.p = Pattern.compile(reg);
    }

    public boolean getResult() {
        return m.matches();
    }

    public String replace(String s) {
        return m.replaceAll(s);
    }

    public String[] getSplit(String s) {
        return p.split(s);
    }
}

public class RegexDemo1 {
    public static void main(String[] args) {
        Regex r = new Regex("[0-9]+", "123890");
        if (r.getResult()) {
            System.out.println("由数字组成");
        } else {
            System.out.println("不全是数字");
        }

        r = new Regex("\\d{4}-\\d{2}-\\d{2}", "1988-09-10");
        if (r.getResult()) {
            System.out.println("日期格式符合要求");
        } else {
            System.out.println("日期格式不符合要求");
        }

        r = new Regex("\\d", "12a89b");
        System.out.println(r.replace("_"));

        r = new Regex("\\d+");
        for (String s : r.getSplit("abc123def456")) {
            System.out.print(s + "\t");
        }
        System.out.println();

        //使用String类的正则
        String s = "aa123bb456cc";
        if (s.matches("\\d")) {
            System.out.println("是数字");
        }
        System.out.println(s.replaceAll("\\d", "_"));
        String[] ss = s.split("\\d+");
        for (int i = 0; i < ss.length; i++) {
            System.out.print(ss[i] + "\t");
        }
        System.out.println();

        String regexStr = "^.*#.*#.*#.*$";
        String str = "10001#2018-07-31 16:05:39#000#GameEnd";
        Matcher matcher = Pattern.compile(regexStr).matcher(str);
        System.out.println(matcher.groupCount());
        for (int i = 0; i < matcher.groupCount(); i++) {
            System.out.println(matcher.group(i));
        }

        Pattern p = Pattern.compile("([a-z]+)(\\d+)");
        Matcher m = p.matcher("aaa2223bb");
        System.out.println(m.find());   //匹配aaa2223
        System.out.println(m.groupCount());   //返回2,因为有2组
        System.out.println(m.start(1));   //返回0 返回第一组匹配到的子字符串在字符串中的索引号
        System.out.println(m.start(2));   //返回3
        System.out.println(m.end(1));   //返回3 返回第一组匹配到的子字符串的最后一个字符在字符串中的索引位置.
        System.out.println(m.end(2));   //返回7
        System.out.println(m.group(1));   //返回aaa,返回第一组匹配到的子字符串
        System.out.println(m.group(2));   //返回2223,返回第二组匹配到的子字符串

        regexStr = "^lyb_test_%s_format_.*$";

        Pattern p1 = Pattern.compile("^CREATE EXTERNAL TABLE");
        System.out.println(p1.matcher("CREATE EXTERNAL TABLE T1").find());
        System.out.println("server string,".split(" ")[0]);

        Pattern pattern = Pattern.compile("\\[(\\d{4}-\\d{1,2}-\\d{1,2}) (\\d{2}:\\d{2}:\\d{2})\\]\\s*\\[((\\d|\\w)+)\\],(.*)$");
        Matcher mat = pattern.matcher("[2019-01-01 10:00:00][log_id],{\"key\":\"value\",\"k\":\"v\"}");
        System.out.println(mat.matches());
        System.out.println(mat.groupCount());
        System.out.println(mat.group(0));
        for (int i = 1; i <= mat.groupCount(); i++) {
            System.out.println(mat.group(i));
        }
        System.out.println(mat.group(5));
        System.out.println(mat.group(1));
        System.out.println(mat.group(1) + " " + mat.group(2));
        System.out.println(mat.group(3));

        JSONObject object = JSONObject.parseObject("{\"a\":\"A\",\"b\":\"B\"}");
        object.put("a", 1);
        System.out.println(object.get("a"));
        JSONObject object2 = (JSONObject) object.clone();
        object2.put("c", "C");
        System.out.println(object.get("c"));
        System.out.println(object2.get("c"));

        System.out.println(JSONObject.isValidObject("{\"a\":[\"A\",\"D\"],AF\"b\":\"B\"}"));

        System.out.println(isJSONObject("[{\"es_schema\":\"luoge-dev.lyb_test123\",\"log_schema\":\"com.netease.fuxi.luoge.nsh.model.tables.v1.lybChannelChat\",\"relation\":\"{\\\"message\\\":[\\\"msg\\\",\\\"role_id\\\"],\\\"role_id\\\":[\\\"role_id1\\\"],\\\"role_level\\\":[\\\"role_level\\\"]}\"},{\"es_schema\":\"luoge-dev.lyb_test123\",\"log_schema\":\"com.netease.fuxi.luoge.nsh.model.tables.v2.lybChannelChat\",\"relation\":\"{\\\"message\\\":[\\\"msg\\\",\\\"role_id\\\"],\\\"role_id\\\":[\\\"role_id1\\\"],\\\"role_level2\\\":[\\\"role_level\\\"]}\"},{\"es_schema\":\"luoge-dev.lyb_test123\",\"log_schema\":\"com.netease.fuxi.luoge.nsh.model.tables.v2.lybtest\",\"relation\":\"\\\"role_id\\\":[\\\"role_id1\\\",\\\"role_id2\\\"],\\\"role_level3\\\":[\\\"role_level\\\"]}\"}]")
                || isJSONArray("[{\"es_schema\":\"luoge-dev.lyb_test123\",\"log_schema\":\"com.netease.fuxi.luoge.nsh.model.tables.v1.lybChannelChat\",\"relation\":\"{\\\"message\\\":[\\\"msg\\\",\\\"role_id\\\"],\\\"role_id\\\":[\\\"role_id1\\\"],\\\"role_level\\\":[\\\"role_level\\\"]}\"},{\"es_schema\":\"luoge-dev.lyb_test123\",\"log_schema\":\"com.netease.fuxi.luoge.nsh.model.tables.v2.lybChannelChat\",\"relation\":\"{\\\"message\\\":[\\\"msg\\\",\\\"role_id\\\"],\\\"role_id\\\":[\\\"role_id1\\\"],\\\"role_level2\\\":[\\\"role_level\\\"]}\"},{\"es_schema\":\"luoge-dev.lyb_test123\",\"log_schema\":\"com.netease.fuxi.luoge.nsh.model.tables.v2.lybtest\",\"relation\":\"\\\"role_id\\\":[\\\"role_id1\\\",\\\"role_id2\\\"],\\\"role_level3\\\":[\\\"role_level\\\"]}\"}]"));

        System.out.println("luoge-dev.lyb_test123".split("\\.")[0]);

    }

    @Test
    public void test1() {
        String regStr_mysql = "mysql\\.(.*)_on_(.*)\\.(.*)";
        Matcher matcher = Pattern.compile(regStr_mysql).matcher("mysql.all_on_mysql-console.console");
        if (matcher.matches()) {
            System.out.println(matcher.group(1)
                    + "\n" + matcher.group(2)
                    + "\n" + matcher.group(3));
        }
    }

    private static boolean isJSON(String str) {
        return isJSONObject(str) || isJSONArray(str);
    }

    private static boolean isJSONObject(String str) {
        boolean result = false;
        try {
            if (!str.trim().equals("")) {
                JSONObject obj = JSONObject.parseObject(str);
                result = true;
            } else {
                result = false;
            }

        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    private static boolean isJSONArray(String str) {
        boolean result = false;
        try {
            if (!str.trim().equals("")) {
                JSONArray obj = JSONObject.parseArray(str);
                result = true;
            } else {
                result = false;
            }

        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
