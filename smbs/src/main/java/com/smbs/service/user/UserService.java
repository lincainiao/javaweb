package com.smbs.service.user;

import com.smbs.pojo.Role;
import com.smbs.pojo.User;

import java.util.List;

public interface UserService {
    // 用户登录
    public User LoginSer(String userCode, String password);

    // 根据用户id修改密码
    public boolean updatePwdSer(int id, String pwd);

    // 查询用户总数
    public int getUserCountSer(String userName,int userRole) throws Exception;

    // 根据条件查询用户列表
    public List<User> getUserListSer(String queryUserName,int queryUserRole,int currentPageNo,int pageSize) throws Exception;

}
