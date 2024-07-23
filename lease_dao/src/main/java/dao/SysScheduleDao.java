package dao;

import pojo.SysSchedule;

import java.util.List;

public interface SysScheduleDao {
    Long getCount(Integer uid, String keyWords);

    List<SysSchedule> getScheduleByUidPage(Integer uid, Integer beginIndex, Integer pageSize, String keyWords);

    int removeSchedule(int sid);

    SysSchedule findScheduleById(Integer sid);

    int updateSchedule(SysSchedule sysSchedule);
}
