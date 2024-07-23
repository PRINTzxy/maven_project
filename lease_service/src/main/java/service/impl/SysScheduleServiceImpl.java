package service.impl;

import dao.SysScheduleDao;
import dao.impl.SysScheduleDaoImpl;
import pojo.SysSchedule;
import service.SysScheduleService;

import java.util.List;

public class SysScheduleServiceImpl implements SysScheduleService {
    private SysScheduleDao sysScheduleDao = new SysScheduleDaoImpl();

    @Override
    public Long getCount(Integer uid, String keyWords) {
        return sysScheduleDao.getCount(uid, keyWords);
    }

    @Override
    public List<SysSchedule> getScheduleByUidPage(Integer uid, Integer pageNum, Integer pageSize, String keyWords) {
        Integer beginIndex = (pageNum - 1)*pageSize;
        return sysScheduleDao.getScheduleByUidPage(uid, beginIndex, pageSize, keyWords);
    }

    @Override
    public int removeSchedule(int sid) {
        return sysScheduleDao.removeSchedule(sid);
    }

    @Override
    public SysSchedule findScheduleById(Integer sid) {
        return sysScheduleDao.findScheduleById(sid);
    }

    @Override
    public int updateSchedule(SysSchedule sysSchedule) {
        return sysScheduleDao.updateSchedule(sysSchedule);
    }
}
