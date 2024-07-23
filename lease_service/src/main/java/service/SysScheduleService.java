package service;

import pojo.SysSchedule;

import java.util.List;

public interface SysScheduleService {
    Long getCount(Integer uid, String keyWords);


    List<SysSchedule> getScheduleByUidPage(Integer uid, Integer pageNum, Integer pageSize, String keyWords);

    int removeSchedule(int sid);

    SysSchedule findScheduleById(Integer sid);

    int updateSchedule(SysSchedule sysSchedule);
}
