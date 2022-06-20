package com.smbs.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    static {
        InputStream in = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();

        try {
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConn() throws Exception{
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    // 关闭连接
    public static boolean closeConn(Connection connection, PreparedStatement statement, ResultSet resultSet){
        boolean flag = true;
        if (resultSet != null) {
            try {
                resultSet.close();// gc回收
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }

        }
        if (statement != null) {
            try {
                statement.close();// gc回收
                statement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }

        }
        if (connection != null) {
            try {
                connection.close();// gc回收
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }

        }
        return flag;
    }

    // 查询操作
    public static ResultSet executeQ(Connection connection,PreparedStatement statement,ResultSet resultSet,String sql,Object[] params) throws Exception{
        statement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i+1,params[i]);
        }
        resultSet = statement.executeQuery();
        return resultSet;
    }
    // 增、删、改
    public static int executeI(Connection connection,PreparedStatement statement,ResultSet resultSet,String sql,Object[] params) throws Exception{
        statement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i+1,params[i]);
        }
        int i = statement.executeUpdate();
        return i;
    }

}
