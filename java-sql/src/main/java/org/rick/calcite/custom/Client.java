package org.rick.calcite.custom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Hello world!
 */
public class Client {
    public static void main(String[] args) {
        try {
            /**
             * 用文件的方式
             * */
//            URL url = Client.class.getResource("/model.json");
//            String str = URLDecoder.decode(url.toString(), "UTF-8");
//            Properties info = new Properties();
//            info.put("model", str.replace("file:", ""));
//            Connection connection = DriverManager.getConnection("jdbc:calcite:", info);


            /**
             * 测试的时候用字符串
             * defaultSchema 默认数据库
             * name 数据库名称
             * type custom
             * factory 请求接收类，该类会实例化Schema也就是数据库类，Schema会实例化Table实现类，Table会实例化数据类。
             * operand 动态参数，ScheamFactory的create方法会接收到这里的数据
             * */
            String model = "{\"version\":\"1.0\",\"defaultSchema\":\"TEST\",\"schemas\":[{\"name\":\"TEST\",\"type\":\"custom\",\"factory\":\"org.rick.calcite.custom.CustomSchemaFactory\",\"operand\":{}}]}";
            Connection connection = DriverManager.getConnection("jdbc:calcite:model=inline:" + model);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from test01");
            while (resultSet.next()) {
                System.out.println("data => ");
                System.out.println(resultSet.getObject("value"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}