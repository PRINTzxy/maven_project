package service.impl;

import dao.SysUserDao;
import dao.impl.SysUserDaoImpl;
import pojo.SysUser;
import service.SysUserService;

public class SysUserServiceImpl implements SysUserService {
    private SysUserDao sysUserDao = new SysUserDaoImpl();

    @Override
    public SysUser getUserByUserName(String username) {
        return sysUserDao.getUserByUserName(username);
    }

    @Override
    public int regist(SysUser user) {
        return sysUserDao.regist(user);
    }
}
