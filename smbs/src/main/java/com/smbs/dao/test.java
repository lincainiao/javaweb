package com.smbs.dao;

import java.sql.Connection;

public class test {
    public static void main(String[] args) throws Exception {
        Connection conn = BaseDao.getConn();
    }
}
