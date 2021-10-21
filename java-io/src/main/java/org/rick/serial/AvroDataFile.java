package org.rick.serial;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AvroDataFile {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Schema
        String schemaDescription = "{\"type\":\"record\",\"name\":\"FacebookUser\",\"fields\":[{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"num_likes\",\"type\":[\"int\",\"null\"],\"default\":0},{\"name\":\"num_photos\",\"type\":\"int\"},{\"name\":\"num_groups\",\"type\":\"int\"}]}\n";




        Schema schema = Schema.parse(schemaDescription);    //parse方法在当前的Avro版本下已不推荐使用
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(writer);
        dataFileWriter.create(schema, os);

        // Populate data
        GenericRecord datum = new GenericData.Record(schema);
//        datum.put("name", new org.apache.avro.util.Utf8("kazaff"));
//        datum.put("num_likes", 1);
        datum.put("num_groups", 423);
        datum.put("num_photos", 10);

        System.out.println(datum.get("num_liks"));

        dataFileWriter.append(datum);
        dataFileWriter.close();

        System.out.println("encoded string: " + os.toString());    //可以看到，数据是头部携带了schema metadata

        // Decode
        DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>();
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        DataFileStream<GenericRecord> dataFileReader = new DataFileStream<GenericRecord>(is, reader);

        GenericRecord record = null;
        while (dataFileReader.hasNext()) {
            record = dataFileReader.next(record);
            System.out.println(record.getSchema());    //可以直接获取该数据的json schema定义
            System.out.println(record.get("name")==null? "this is null":record.get("name").toString());
            System.out.println(record.get("num_likes"));
            System.out.println(record.get("num_groups").toString());
            System.out.println(record.get("num_photos").toString());
        }
    }
}