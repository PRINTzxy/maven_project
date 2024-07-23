package controller;

import common.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.SysSchedule;
import pojo.SysUser;
import service.SysScheduleService;
import service.SysUserService;
import service.impl.SysScheduleServiceImpl;
import service.impl.SysUserServiceImpl;
import util.WebUtils;
import vo.PageKeyWordsVo;
import vo.PageVo;

import java.io.IOException;
import java.util.List;

@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {
    private SysScheduleService sysScheduleService = new SysScheduleServiceImpl();

    protected void getScheduleByUid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageKeyWordsVo pageKeyWordsVo = WebUtils.readJson(PageKeyWordsVo.class,req);

        SysUser sysUser = (SysUser) req.getSession().getAttribute("sysUser");

        Integer uid = sysUser.getUid();

        Integer pageNum = pageKeyWordsVo.getPageNum()==null?1: pageKeyWordsVo.getPageNum();;
        Integer pageSize = pageKeyWordsVo.getPageSize()==null?2: pageKeyWordsVo.getPageSize();;

        Long total = sysScheduleService.getCount(uid,pageKeyWordsVo.getKeyWords());

        Long totalPage = total %pageSize==0?total/pageSize:total/pageSize+1;

        List<SysSchedule> scheduleList = sysScheduleService.getScheduleByUidPage(uid, pageNum, pageSize, pageKeyWordsVo.getKeyWords());

        PageVo pageVo = new PageVo();
        pageVo.setScheduleList(scheduleList);
        pageVo.setPageNum(pageNum);
        pageVo.setPageSize(pageSize);
        pageVo.setTotalPage(totalPage);
        pageVo.setKeyWords(pageKeyWordsVo.getKeyWords());

        WebUtils.writeJson(Result.ok(200,"success",pageVo),resp);

    }


    protected void addSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));

        int row = sysScheduleService.removeSchedule(sid);
        if (row == 1){
            WebUtils.writeJson(Result.ok(),resp);
        }
    }


    protected void findScheduleById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sid = Integer.parseInt(req.getParameter("sid"));

        SysSchedule sysSchedule = sysScheduleService.findScheduleById(sid);

        //保存日程对象到作用域
        req.getSession().setAttribute("schedule",sysSchedule);
        //跳转到修改的页面  ————>   实现回显
        resp.sendRedirect("/updateSchedule.jsp");
    }


    protected void updateSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sid = Integer.parseInt(req.getParameter("sid"));

        String title = req.getParameter("title");

        Integer completed = Integer.parseInt(req.getParameter("completed"));

        SysSchedule sysSchedule = new SysSchedule();
        sysSchedule.setSid(sid);
        sysSchedule.setCompleted(completed);
        sysSchedule.setTitle(title);

        int row = sysScheduleService.updateSchedule(sysSchedule);

        resp.setContentType("text/html");
        if (row == 1){
            resp.getWriter().write("<script>alert('修改成功!');location.href='/schedule/getScheduleByUid'</script>");
        }

    }
}
