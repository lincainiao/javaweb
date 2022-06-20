package com.lin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.IdentityHashMap;

public class TestJDBC {
    public static void main(String[] args) throws Exception {
        // 配置信息
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "root";

        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        // 3.sql执行语句
        String sqlQuery = "select * from users";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        // 4.执行sql
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println("id="+resultSet.getObject("id"));
            System.out.println("name="+resultSet.getObject("name"));
            System.out.println("password="+resultSet.getObject("password"));
            System.out.println("email="+resultSet.getObject("email"));
            System.out.println("birthday="+resultSet.getObject("birthday"));
        }

        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null){
            connection.close();
        }
    }
}
