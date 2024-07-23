<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pojo.SysSchedule" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <style>

    .ht{
      text-align: center;
      color: cadetblue;
      font-family: 幼圆;
    }
    .tab{
      width: 80%;
      border: 5px solid cadetblue;
      margin: 0px auto;
      border-radius: 5px;
      font-family: 幼圆;
    }
    .ltr td{
      border: 1px solid  powderblue;

    }
    .ipt{
      border: 0px;
      width: 50%;

    }
    .btn1{
      border: 2px solid powderblue;
      border-radius: 4px;
      width:100px;
      background-color: antiquewhite;

    }
    #usernameMsg , #userPwdMsg {
      color: gold;
    }

    .buttonContainer{
      text-align: center;
    }
  </style>


</head>
<body>

<%
 Integer sid=Integer.parseInt(request.getParameter("sid"));
 String title=request.getParameter("title");
  Integer completed=Integer.parseInt(request.getParameter("completed"));
  SysSchedule sysSchedule=new SysSchedule();
  sysSchedule.setTitle(title);
  sysSchedule.setSid(sid);
  sysSchedule.setCompleted(completed);
  session.setAttribute("schedule",sysSchedule);

%>

<h1 class="ht">欢迎使用日程管理系统</h1>
<h3 class="ht">日程修改</h3>
<form action="/schedule/updateSchedule" method="post">
  <%--隐藏域：不显示数据给用户看,后端需要--%>
  <input type="hidden" name="sid" value="${schedule.sid}">
<table class="tab" cellspacing="0px">
  <tr class="ltr">
    <th>内容</th>
    <th>进度</th>
  </tr>
  <tr class="ltr">
    <td><input type="text" name="title" value="${schedule.title}"> </td>
    <td>
      <input type="radio" name="completed" value="1" ${schedule.completed==1?'checked':''}> 已完成
      <input type="radio" name="completed" value="0" ${schedule.completed==0?'checked':''}> 未完成
    </td>
    <td class="buttonContainer">
      <button class="btn1">保存</button>
    </td>
  </tr>
</table>
</form>
</body>
</html>