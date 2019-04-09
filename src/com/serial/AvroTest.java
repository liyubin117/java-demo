package com.serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

public class AvroTest {
    public static void main(String[] args) throws IOException {
        // Schema
        String schemaDescription = " {    \n"
                + " \"name\": \"FacebookUser\", \n"
                + " \"type\": \"record\",\n" + " \"fields\": [\n"
                + "   {\"name\": \"num_groups\", \"type\": \"int\"} ]\n" + "}";

        Schema s = new Schema.Parser().parse(schemaDescription);    //parse方法在当前的Avro版本下已不推荐使用
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
        GenericDatumWriter w = new GenericDatumWriter(s);

        // Populate data
        GenericRecord r = new GenericData.Record(s);
        r.put("num_groups", 0);

        // Encode
        w.write(r, encoder);
        encoder.flush();

        byte[] encodedByteArray = outputStream.toByteArray();
        String encodedString = outputStream.toString();

        System.out.println("encodedString: "+encodedString);

        // Decode using same schema
        DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(s);
        Decoder decoder = DecoderFactory.get().binaryDecoder(encodedByteArray, null);
        GenericRecord result = reader.read(null, decoder);
        System.out.println(result.get("num_groups").toString());


        schemaDescription = " {    \n"
                + " \"name\": \"kafka2hdfs\", \n"
                + " \"type\": \"record\",\n" + " \"fields\": [\n"
                + "   {\"name\": \"header_filepath\", \"type\": \"string\"},\n"
                + "   {\"name\": \"account_id\", \"type\": \"string\"},\n"
                + "   {\"name\": \"account_id2\", \"type\": \"string\"},\n"
                + "   {\"name\": \"chat_content\", \"type\": \"string\"},\n"
                + "   {\"name\": \"chat_time\", \"type\": \"string\"},\n"
                + "   {\"name\": \"ip\", \"type\": \"string\"},\n"
                + "   {\"name\": \"ip2\", \"type\": \"string\"},\n"
                + "   {\"name\": \"log_id\", \"type\": \"string\"},\n"
                + "   {\"name\": \"log_ts\", \"type\": \"string\"},\n"
                + "   {\"name\": \"mac_addr\", \"type\": \"string\"},\n"
                + "   {\"name\": \"mac_addr2\", \"type\": \"string\"},\n"
                + "   {\"name\": \"r_time\", \"type\": \"string\"},\n"
                + "   {\"name\": \"r_way\", \"type\": \"string\"},\n"
                + "   {\"name\": \"reason\", \"type\": \"string\"},\n"
                + "   {\"name\": \"role_id\", \"type\": \"string\"},\n"
                + "   {\"name\": \"role_id2\", \"type\": \"string\"},\n"
                + "   {\"name\": \"role_level\", \"type\": \"string\"},\n"
                + "   {\"name\": \"role_level2\", \"type\": \"string\"},\n"
                + "   {\"name\": \"role_name\", \"type\": \"string\"},\n"
                + "   {\"name\": \"role_name2\", \"type\": \"string\"},\n"
                + "   {\"name\": \"server_id\", \"type\": \"string\"},\n"
                + "   {\"name\": \"u_vip1\", \"type\": \"string\"},\n"
                + "   {\"name\": \"u_vip2\", \"type\": \"string\"},\n"
                + "   {\"name\": \"udid\", \"type\": \"string\"},\n"
                + "   {\"name\": \"udid2\", \"type\": \"string\"} ]\n" + "}";
        Schema schema = new Schema.Parser().parse(schemaDescription);

        String originJson = "{\"account_id2\":\"aebfwu2k3e3schvm@ad.netease.win.163.com\",\"reason\":\"\",\"r_time\":1550800058539,\"chat_time\":1550800058539,\"role_level\":40,\"log_ts\":\"2019-02-22 09:47:38\",\"role_id2\":100722644,\"r_way\":4,\"mac_addr2\":\"D8:9A:34:20:88:CD\",\"role_id\":203626142,\"mac_addr\":\"02:00:00:00:00:00\",\"udid2\":\"fd5dad385185aeb3\",\"udid\":\"D2AA29CC-D853-44B5-88FB-725E7E65ACF8\",\"r_type\":[1,3],\"log_id\":\"CheatReport\",\"u_vip1\":6,\"ip\":\"114.242.249.188\",\"u_vip2\":2,\"ip2\":\"112.12.244.19\",\"chat_content\":\"\",\"server_id\":2036,\"role_name\":203626142,\"role_level2\":50,\"account_id\":\"aebfqe4ylmhrj2hd@ios.netease.win.163.com\",\"role_name2\":\"篮板无人敌\",\"log_ds\":\"2019-02-22\"}";
        r = new GenericData.Record(schema);
        r.put("header_filepath", new org.apache.avro.util.Utf8("header_filepath"));
        List<Schema.Field> fieldList = schema.getFields();
        List<String> list = new ArrayList<>();
        fieldList.forEach(x -> list.add(x.name()));
        for(Map.Entry<String,Object> e2: JSONObject.parseObject(originJson).entrySet()){
            if(list.contains(e2.getKey())){
                System.out.println(e2.getKey()+" "+e2.getValue().toString());
                r.put(e2.getKey(), new org.apache.avro.util.Utf8(e2.getValue().toString()));
            }
        }
        System.out.println(r.get("account_id").toString());

        StringBuilder builder = new StringBuilder();
        builder.append("},\n");
        System.out.println(builder);
        int length = builder.length();
        builder.replace(length-2,length-1,"");
        System.out.println(builder);

        String schemaDesc = "{\"account_id2\":\"aebfwu2k3e3schvm@ad.netease.win.163.com\",\"reason\":\"\",\"r_time\":1550800058539,\"chat_time\":1550800058539,\"role_level\":40,\"log_ts\":\"2019-02-22 09:47:38\",\"role_id2\":100722644,\"r_way\":4,\"mac_addr2\":\"D8:9A:34:20:88:CD\",\"role_id\":203626142,\"mac_addr\":\"02:00:00:00:00:00\",\"udid2\":\"fd5dad385185aeb3\",\"udid\":\"D2AA29CC-D853-44B5-88FB-725E7E65ACF8\",\"r_type\":[1,3],\"log_id\":\"CheatReport\",\"u_vip1\":6,\"ip\":\"114.242.249.188\",\"u_vip2\":2,\"ip2\":\"112.12.244.19\",\"chat_content\":\"\",\"server_id\":2036,\"role_name\":203626142,\"role_level2\":50,\"account_id\":\"aebfqe4ylmhrj2hd@ios.netease.win.163.com\",\"role_name2\":\"篮板无人敌\",\"log_ds\":\"2019-02-22\"}";
        System.out.println(jsonToSchema(JSONObject.parseObject(schemaDesc)));
        schema = new Schema.Parser().parse(jsonToSchema(JSONObject.parseObject(schemaDesc)));
        schema.getFields().forEach(x-> System.out.println(x));
        System.out.println(schema.getField("account_id2").schema());
        System.out.println(schema.getType());

        GenericRecord record = new GenericData.Record(schema);

        for(Map.Entry<String,Object> e: JSONObject.parseObject(schemaDesc).entrySet()){
            String fieldName = e.getKey();
            if(schema.getField(fieldName).schema().equals("\"string\"")){
                record.put(fieldName, new org.apache.avro.util.Utf8(e.getValue().toString()));
            }else{
                record.put(fieldName, e.getValue());
            }
        }

        System.out.println(record.get("role_level2"));

        System.out.println("--------------");
        Schema schema2 = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"test\",\"fields\":[{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"id\",\"type\":[\"int\",\"null\"],\"default\":0},{\"name\":\"num1\",\"type\":\"int\"},{\"name\":\"num2\",\"type\":\"int\"}]}\n");
        GenericRecord record2 = new GenericData.Record(schema2);
        record2.put("num2",10);
        System.out.println(record2.get("id"));
        System.out.println(record2.get("name"));
        System.out.println(record2.get("num2"));

        schema = new Schema.Parser().parse("{\n" +
                " \"name\": \"Skill\",\n" +
                " \"type\": \"record\",\n" +
                " \"fields\": [\n" +
                "   {\"name\": \"log_id\", \"type\": [\"string\",\"null\"], \"default\":\"\"},\n" +
                "   {\"name\": \"mentor_id\", \"type\": [\"int\",\"null\"], \"default\":0},\n" +
                "   {\"name\": \"role_type\", \"type\": [\"string\",\"null\"], \"default\":\"\"},\n" +
                "   {\"name\": \"game_uuid\", \"type\": [\"string\",\"null\"], \"default\":\"\"},\n" +
                "   {\"name\": \"role_id\", \"type\": [\"int\",\"null\"], \"default\":0},\n" +
                "   {\"name\": \"game_remain_time\", \"type\": [\"double\",\"null\"], \"default\":0},\n" +
                "   {\"name\": \"skill_id\", \"type\": [\"int\",\"null\"], \"default\":0},\n" +
                "   {\"name\": \"mentor_run\", \"type\": [\"int\",\"null\"], \"default\":0},\n" +
                "   {\"name\": \"model_id\", \"type\": [\"int\",\"null\"], \"default\":0},\n" +
                "   {\"name\": \"log_ds\", \"type\": [\"string\",\"null\"], \"default\":\"\"},\n" +
                "   {\"name\": \"log_ts\", \"type\": [\"string\",\"null\"], \"default\":\"\"}\n" +
                "]}");
        record = new GenericData.Record(schema);
        JSONObject json = JSONObject.parseObject("{\"log_id\":\"Skill\",\"role_type\":\"user\",\"game_uuid\":\"07a1fdbf8070576da81cfa2305ba9791\",\"game_remain_time\":67.30963134765625,\"skill_id\":1,\"mentor_run\":0,\"model_id\":66,\"log_ds\":\"2019-02-19\",\"log_ts\":\"2019-02-19 19:46:45\"}");
        //,"role_id":104154220
        for(Map.Entry<String,Object> e: json.entrySet()){
            String fieldName = e.getKey();
            if(schema.getField(fieldName).schema().toString().equals("\"string\"")){
                record.put(fieldName, new org.apache.avro.util.Utf8(e.getValue().toString()));
            }else{
                record.put(fieldName, e.getValue());
            }
        }
        System.out.println(record.get("role_id"));
        System.out.println(schema.getField("log_id").schema().toString().contains("\"string\""));

    }


    private static String jsonToSchema(JSONObject json){
        StringBuilder builder = new StringBuilder();
        builder.append(" {    \n"
                + " \"name\": \""+json.getString("log_id")+"\", \n"
                + " \"type\": \"record\",\n" + " \"fields\": [\n");
        for(Map.Entry entry:json.entrySet()){
            builder.append("   {\"name\": \""+entry.getKey()+"\", \"type\": \""+getType(entry.getValue())+"\"},\n");
        }
        int length = builder.length();
        builder.replace(length-2,length-1,"");
        builder.append("]}");
        return builder.toString();
    }

    private static String getType(Object o){
        if (o instanceof String) return "string";
        if (o instanceof Number) {
            String s = o.toString();
            if (s.indexOf('.') > 0) {
                return "double";
            } else {
                if(s.length() > 9) return "long";
                else return "int";
            }
        }
        if (o instanceof Boolean) return "boolean";
        return "string";
    }
}