package org.rick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class OracleTest {
    public static void main(String[] args) {

        //声明连接对象、执行sql对象、结果集对象
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        //添加jar驱动 ojdbc6.jar
        try{

            //注册驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //获取连接数据库对象
            Properties properties = new Properties();
            properties.load(OracleTest.class.getClassLoader().getResourceAsStream("oracle.prop"));

            String url = properties.getProperty("jdbc");
            String name = properties.getProperty("user");
            String pwd = properties.getProperty("password");
            //DriverManage初始化时会扫描到注册的Driver实例
            conn = DriverManager.getConnection(url,name,pwd);
            System.out.println("连接成功");
            //定义要执行的sql
            String sql = "SELECT ENAME,JOB FROM EMP";
            //获取执行sql对象
            pstat = conn.prepareStatement(sql);
            pstat.execute("alter session set current_schema=scott");
            //执行SQL并返回结果集
            rs = pstat.executeQuery(sql);
            System.out.println("执行成功");

            while(rs.next()){
                System.out.println("名字："+ rs.getString(1) + "职称：" + rs.getString(2));
            }
        }catch (ClassNotFoundException  e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(rs != null){

                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(pstat != null){

                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null){

                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}