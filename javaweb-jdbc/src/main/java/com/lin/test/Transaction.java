package com.lin.test;

import org.junit.Test;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {

    @Test
    public void test() throws Exception{
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "root";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,username,password);

        connection.setAutoCommit(false);

        String sql = "update account set money = money-100 where id = 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        int y = 1/0;

        sql = "update account set money = money+100 where id = 2";
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        connection.commit();
    }
}
