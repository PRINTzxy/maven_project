package controller;

import common.Result;
import common.ResultCodeEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.SysUser;
import service.SysUserService;
import service.impl.SysUserServiceImpl;
import util.MD5Util;
import util.WebUtils;

import java.io.IOException;

@WebServlet(urlPatterns = "/user/*")
public class SysUserController extends BaseController {
    private SysUserService sysUserService = new SysUserServiceImpl();
    public SysUserController(){super();}

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");

        SysUser sysUser = sysUserService.getUserByUserName(username);

        if (sysUser == null){
            resp.sendRedirect("/loginUsernameError.html");
        }else {
            if (!sysUser.getUserPwd().equals(MD5Util.encrypt(userpwd))){
                resp.sendRedirect("/loginUsernameError.html");
            }else {
                req.getSession().setAttribute("sysUser", sysUser);
                resp.sendRedirect("/showSchedule.html");
            }
        }
    }



    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");

        SysUser sysUser = sysUserService.getUserByUserName(username);

        if (sysUser == null){
            SysUser user = new SysUser();
            user.setUserName(username);
            user.setUserPwd(userpwd);

            int row = sysUserService.regist(user);
            if (row > 0){
                resp.sendRedirect("/registSuccess.html");
            }
        }else {
            resp.sendRedirect("/registFail.html");
        }
    }


    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/login.html");
    }


    protected void checkUserNameUesd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        SysUser sysUser = sysUserService.getUserByUserName(username);

        resp.setContentType("text/html");

        Result<Object> result = null;
        if (sysUser == null){
            result = Result.ok(ResultCodeEnum.SUCCESS, "用户名可用");
        }else {
            result = Result.build(ResultCodeEnum.USERNAME_USED, "用户名已被使用");
        }
        WebUtils.writeJson(result,resp);

    }
}
