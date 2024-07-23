package dao;

import pojo.SysUser;

public interface SysUserDao {
    SysUser getUserByUserName(String username);

    int regist(SysUser user);
}
