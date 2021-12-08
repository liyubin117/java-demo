package org.rick.jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JedisTest {
	private String host="fuxi-luoge-76";
	private int port=7111;
	private String password="passwd";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void single() {
		// 连接单实例
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		// 保存数据
//		jedis.set("name", "liyubin1");
//		jedis.set("priv", "result:{\"items\":[{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"file:///var/lib/hive/elasticsearch-hadoop-hive-6.4.0.jar\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"hdfs://kf-nameservice/user/hive/udf\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"file:///var/lib/hive/java-schema-1.0.jar\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"SERVER\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"file:///var/lib/hive/json-serde-1.3.8-jar-with-dependencies.jar\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"file:///var/lib/hive/hive-hcatalog-core-1.1.0-cdh5.15.1.jar\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"file:///var/lib/hive/fuxi-java-schema-1.0.jar\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"file:///var/lib/hive/luoge-dataset-1.0-SNAPSHOT.jar\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"hdfs:///user/hive/udf/json-serde-1.3.8-jar-with-dependencies.jar\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"hdfs://kf-nameservice/user/hive/udf/\"},{\"action\":\"select\",\"column\":\"\",\"database\":\"default\",\"scope\":\"DATABASE\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"file:///var/lib/hive/dwd_nsh_pfv_enter_leave_mapping_add_d.py\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"hdfs://kf-nameservice/user/hive/udf/json-serde-1.3.8-jar-with-dependencies.jar\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"hdfs:///user/hive/udf/\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"file:///var/lib/hive/luoge-dataset-1.0-20190311.124627-2.jar\"},{\"action\":\"*\",\"column\":\"\",\"database\":\"\",\"scope\":\"URI\",\"server\":\"server1\",\"table\":\"\",\"uri\":\"hdfs://kf-nameservice/user/hive/udf/hive-hcatalog-core-1.1.0-cdh5.15.1.jar\"}],\"total\":16}");
//
//		jedis.expire("name", 30);

		System.out.println(jedis.exists("name"));
		// 获取数据
		System.out.println(jedis.get("name"));
		jedis.close();
	}

	//单实例测试
	//连接池
	@Test
	public void pool(){
		//获得连接池对象
		JedisPoolConfig conf=new JedisPoolConfig();
		//设置最大连接数
		conf.setMaxTotal(30);
		//设置最大空闲连接数
		conf.setMaxIdle(10);

		//获得连接池
		JedisPool jedisPool=new JedisPool(conf,host,port);
		//获得核心对象
		Jedis jedis=null;
		try{
			//通过连接池建立连接
			jedis=jedisPool.getResource();
			jedis.auth(password);
			jedis.set("pool", "mypool");
			System.out.println(jedis.get("pool"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(jedis!=null){
				jedis.close();
			}
			if(jedisPool!=null){
				jedisPool.close();
			}
		}
	}

	//对于大批量写入，可以将复杂数据写成json形式存到string类型键里，比pipeline快一些
	@Test
    public void testJson(){
        Jedis jedis = new Jedis(host, port);
        jedis.auth(password);

        Set<Map<String, Object>> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("framework", "spring" + i);
            set.add(map);
        }
        jedis.set("json", JSON.toJSONString(set));

        String value = jedis.get("json");
        // json to list<map<>> 写成工具方法
        Set<Map<String, String>> resultSet = jsonToSetMap(value);
        System.out.println(resultSet);

    }

    public static Map<String, String> jsonToMap(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map<String, String> map = new HashMap<>();
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            map.put(key, jsonObject.get(key).toString());
        }
        return map;
    }

    public static Set<Map<String, String>> jsonToSetMap(String jsonList) {
        Set<Map<String, String>> set = new HashSet<>();
        JSONArray parseArray = JSONArray.parseArray(jsonList);
        for (Object object : parseArray) {
            String jsonString = JSON.toJSONString(object);
            Map<String, String> map = jsonToMap(jsonString);
            set.add(map);
        }
        return set;
    }

}
