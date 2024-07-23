package dao.impl;

import dao.SysUserDao;
import pojo.SysUser;

public class SysUserDaoImpl extends BaseDaoImpl implements SysUserDao {
    @Override
    public SysUser getUserByUserName(String username) {
        return  getBean(SysUser.class,"select uid,username userName,user_pwd userPwd from sys_user where userName=?",username);
    }

    @Override
    public int regist(SysUser user) {
        return commonUpdate("insert into sys_user values(null,?,?)",user.getUserName(),user.getUserPwd());
    }
}
