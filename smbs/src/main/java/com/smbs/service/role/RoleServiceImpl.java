package com.smbs.service.role;

import com.smbs.dao.BaseDao;
import com.smbs.dao.role.RoleDao;
import com.smbs.dao.role.RoleDaoImpl;
import com.smbs.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class RoleServiceImpl implements RoleService{
    private RoleDao roleDao;
    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }
    @Override
    public List<Role> getRoleListSer() throws Exception {
        Connection connection = BaseDao.getConn();
        List<Role> roleList = roleDao.getRoleList(connection);

        BaseDao.closeConn(connection,null,null);
        return roleList;
    }

}
