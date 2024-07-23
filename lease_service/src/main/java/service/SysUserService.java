package service;

import pojo.SysUser;

public interface SysUserService {
    SysUser getUserByUserName(String username);


    int regist(SysUser user);
}
