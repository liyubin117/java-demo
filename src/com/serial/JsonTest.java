package com.serial;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JsonTest {
    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JsonOrgModule());
    }

    @Test
    public void test1() throws JsonProcessingException {
        Set<Map<String, String>> set = new HashSet<>();
        Map<String, String> info_tables_priv = new HashMap<>();
        info_tables_priv.put("action", "select");
        info_tables_priv.put("column", "");
        info_tables_priv.put("database", "information_schema");
        info_tables_priv.put("scope", "TABLE");
        info_tables_priv.put("server", "server1");
        info_tables_priv.put("table", "tables");
        info_tables_priv.put("uri", "");
        set.add(info_tables_priv);
        System.out.println(new JSONObject(set).isEmpty());
        System.out.println(new JSONObject(set).toString());
        System.out.println(new ObjectMapper().writeValueAsString(set));
    }

    @Test
    public void test2() throws JsonProcessingException {
        JSONObject obj = mapper.readValue("{\"k1\":\"v1\",\"k2\":10}", JSONObject.class);
        System.out.println(obj.getInt("k2"));

        System.out.println(mapper.writeValueAsString(obj));
    }
}
