package com.smbs.dao.user;

import com.smbs.pojo.Role;
import com.smbs.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    // 得到要登录的用户
    public User getLoginUser(Connection connection,String userCode);

    // 修改用户密码
    public int updatePwd(Connection connection,int id,String password) throws Exception;

    // 查询用户总数量
    public int getUserCount(Connection connection,String userName,int userRole) throws Exception;

    // 获取用户列表
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception;


}
