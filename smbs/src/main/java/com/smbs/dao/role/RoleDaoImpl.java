package com.smbs.dao.role;

import com.smbs.dao.BaseDao;
import com.smbs.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{
    // 获取角色列表
    @Override
    public List<Role> getRoleList(Connection connection) throws Exception {
        String sql = "select * from smbms_role";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Role> roleList = new ArrayList<>();
        Object[] params = {};
        if (connection != null){
            resultSet = BaseDao.executeQ(connection, statement, resultSet, sql, params);
        }
        while (resultSet.next()) {
            Role role = new Role();
            role.setId(resultSet.getInt("id"));
            role.setRoleCode(resultSet.getString("roleCode"));
            role.setRoleName(resultSet.getString("roleName"));
            roleList.add(role);
        }
        BaseDao.closeConn(null,statement,resultSet);
        return roleList;
    }
}
