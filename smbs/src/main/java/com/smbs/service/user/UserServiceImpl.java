package com.smbs.service.user;

import com.smbs.dao.BaseDao;
import com.smbs.dao.user.UserDao;
import com.smbs.dao.user.UserDaoImpl;
import com.smbs.pojo.User;
import com.smbs.utils.Constant;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

// 业务层都会调用dao层，所以要引入Dao层
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }


    @Override
    public User LoginSer(String userCode, String password) {
        Connection connection = null;
        try {
            connection = BaseDao.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        User loginUser = userDao.getLoginUser(connection, userCode);
        BaseDao.closeConn(connection,null,null);
        if (loginUser.getUserPassword().equals(password)){
            return loginUser;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean updatePwdSer(int id, String pwd) {
        Connection connection = null;
        try {
            connection = BaseDao.getConn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean flag = false;
        // 修改密码
        try {
            if (userDao.updatePwd(connection,id,pwd) > 0){
                // 如果密码修改成功，返回true
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeConn(connection,null, null);
        }

        return flag;
    }

    @Override
    public int getUserCountSer(String userName, int userRole) throws Exception {
        Connection connection = null;
        connection = BaseDao.getConn();
        int userCount = userDao.getUserCount(connection, userName, userRole);

        connection.close();
        return userCount;
    }


    @Override
    public List<User> getUserListSer(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) throws Exception {
        Connection connection = null;
        connection = BaseDao.getConn();
        List<User> userList = userDao.getUserList(connection,queryUserName,queryUserRole,currentPageNo,pageSize);

        connection.close();
        return userList;
    }

//    @Test
//    public void test(){
//        try {
//            List<User> userListSer = this.getUserListSer(null, 0, 1, Constant.maxPageSize);
////            System.out.println(userListSer.size());
//            for (User user:userListSer) {
//                System.out.println(user.getUserName());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
