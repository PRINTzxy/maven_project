package dao.impl;

import dao.SysScheduleDao;
import pojo.SysSchedule;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDaoImpl implements SysScheduleDao {
    @Override
    public Long getCount(Integer uid, String keyWords) {
        if(keyWords==null || keyWords.length()<=0){
            return (Long) getValue("select count(1) from sys_schedule where uid=?",uid);
        }else {
            return (Long) getValue("select count(1) from sys_schedule where uid=? and title like concat('%',?,'%')",uid,keyWords);
        }
    }

    @Override
    public List<SysSchedule> getScheduleByUidPage(Integer uid, Integer beginIndex, Integer pageSize, String keyWords) {
        if(keyWords==null || keyWords.length()<=0){
            return beanList(SysSchedule.class,"select * from sys_schedule where uid=?  limit ?,?",uid,beginIndex,pageSize);
        }else{
            return beanList(SysSchedule.class,"select * from sys_schedule where uid=? and title like concat('%',?,'%')  limit ?,?",uid,keyWords,beginIndex,pageSize);
        }
    }

    @Override
    public int removeSchedule(int sid) {
        return commonUpdate("delete from sys_schedule where sid=?",sid);
    }

    @Override
    public SysSchedule findScheduleById(Integer sid) {
        return getBean(SysSchedule.class,"select * from sys_schedule where sid=?",sid);
    }

    @Override
    public int updateSchedule(SysSchedule sysSchedule) {
        return commonUpdate("update sys_schedule set title=?,completed=? where sid=?",sysSchedule.getTitle(),sysSchedule.getCompleted(),sysSchedule.getSid());
    }
}
