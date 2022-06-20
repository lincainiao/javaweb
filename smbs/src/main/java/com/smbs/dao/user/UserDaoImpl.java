package com.smbs.dao.user;

import com.mysql.cj.util.StringUtils;
import com.smbs.dao.BaseDao;
import com.smbs.pojo.Role;
import com.smbs.pojo.User;
import com.smbs.utils.Constant;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    // 获取登录的用户
    @Override
    public User getLoginUser(Connection connection, String userCode) {
        String sql = "select * from smbms_user where userCode = ?";
        Object[] params = {userCode};
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        if (connection != null) {
            try {
                resultSet = BaseDao.executeQ(connection,statement,resultSet,sql,params);
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUserCode(resultSet.getString("userCode"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setUserPassword(resultSet.getString("userPassword"));
                    user.setGender(resultSet.getInt("gender"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setAddress(resultSet.getString("address"));
                    user.setUserRole(resultSet.getInt("userRole"));
                    user.setCreatedBy(resultSet.getInt("createdBy"));
                    user.setCreationDate(resultSet.getTimestamp("creationDate"));
                    user.setModifyBy(resultSet.getInt("modifyBy"));
                    user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                BaseDao.closeConn(null,statement,resultSet);
            }
        }
        return user;
    }

    // 修改用户密码
    @Override
    public int updatePwd(Connection connection, int id, String password) throws Exception {
        PreparedStatement statement = null;
        int execute = 0;
        if (connection != null) {
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object[] params = {password,id};
            execute = BaseDao.executeI(connection, statement, null, sql, params);
            BaseDao.closeConn(null,statement,null);
        }
        System.out.println("UserDaoImpl : "+password);

        return execute;
    }

    // 根据用户名和角色名查询用户总数
    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Object> list = null;
        StringBuffer sql = null;
        int userCount = 0;
        if (connection != null) {
            sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");

            // 存放参数
            list = new ArrayList<>();

            if (!StringUtils.isNullOrEmpty(userName)){
                sql.append("and u.userName like ?");
                list.add("%"+userName+"%"); // index = 0
            }
            if (userRole > 0){
                sql.append(" and u.userRole = ?");
                list.add(userRole); // index = 1
            }
        }
        // 转换为数组
        Object[] params = list.toArray();
        System.out.println("getUserCount->"+sql);

        resultSet = BaseDao.executeQ(connection, statement, resultSet, sql.toString(), params);

        if (resultSet.next()) {
            // 从结果集获取最终的数量
            userCount = resultSet.getInt("count");
        }

        BaseDao.closeConn(null,statement,resultSet);
        return userCount;
    }

    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Object> paramsList = null;
        List<User> userList = new ArrayList<>();
        StringBuffer sql = null;
        if (connection != null) {
            sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");

            // 存放参数
            paramsList = new ArrayList<>();

            if (!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                paramsList.add("%"+userName+"%"); // index = 0
            }
            if (userRole > 0){
                sql.append(" and u.userRole = ?");
                paramsList.add(userRole); // index = 1
            }
            sql.append(" order by id ASC limit ?,?");
            // 第n页，一页x条记录：limit (n-1)*x,x
            currentPageNo = (currentPageNo-1) * pageSize;
            paramsList.add(currentPageNo);
            paramsList.add(pageSize);
        }
        // 转换为数组
        Object[] params = paramsList.toArray();

        resultSet = BaseDao.executeQ(connection, statement, resultSet, sql.toString(), params);

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUserCode(resultSet.getString("userCode"));
            user.setUserName(resultSet.getString("userName"));
            user.setGender(resultSet.getInt("gender"));
            user.setBirthday(resultSet.getDate("birthday"));
            user.setPhone(resultSet.getString("phone"));
            user.setUserRole(resultSet.getInt("userRole"));
            userList.add(user);
        }


        BaseDao.closeConn(null,statement,resultSet);
        return userList;
    }
}
