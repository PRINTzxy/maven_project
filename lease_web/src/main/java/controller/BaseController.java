package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/*
*  作为所有控制器的父类
* */
public class BaseController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // System.out.println("baseContoller...");

        try {
            //父类中的service方法是处理客户端的请求的唯一入口
            //由父类调用子类的自定义方法



            /*
            *  1.获取客户端请求的资源路径
            *  2.处理具体的资源路径(明确调用子类的哪一个自定义方法)
            *  3.使用反射调用(使用父类的方法调用子类的方法)
            * */

            String requestURI = req.getRequestURI();
            String[] split = requestURI.split("/");
            //获取子类中被调用的方法的名字 比如: /user/login 其中(login就是子类中的自定义方法的名字)
            String methodName = split[split.length - 1];


        /*
           this:当前子类实例对象
        *  获取到目标方法对象
        * */
            Method declaredMethod = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //设置目标方法的可见性
            declaredMethod.setAccessible(true);
            //调用目标方法
            declaredMethod.invoke(this,req,resp);

        } catch (Exception e) {
             e.printStackTrace();
        }


    }
}
