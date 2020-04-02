
<%@ page import="java.util.List" %>
<%@ page import="org.example.javaee.class03.model.Homework" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/3/11
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生查询作业</title>
    <%--    将指定的作业id传给/submit,以便提交作业--%>
    <script>
        function show(id) {
            let homework = document.getElementById('homeworkId')
            homework.setAttribute("value",id)
            let sub = document.getElementById('sub')
            sub.submit()
        }
    </script>
</head>
<body>


<form id="sub" method="get" action="${pageContext.request.contextPath}/submit">
    <input id="homeworkId" name="id" type="hidden">
    <table align="center" width="960" border="1">
        <tr bgcolor="darkorange">
            <th>作业编号</th>
            <th>作业标题</th>
            <th>作业内容</th>
            <th>提交作业</th>
        </tr>

        <%
            List<Homework> list = (List<Homework>)request.getAttribute("list");
            if (list == null || list.size() <= 0){

            }else {
                for (Homework homework : list) {
        %>
        <tr>
            <td width="10%"><%=homework.getId()%></td>
            <td width="20%" style="word-break: break-all;word-wrap: break-word"><%=homework.getTitle()%></td>
            <td width="60%" style="word-break: break-all;word-wrap: break-word"><%=homework.getContent()%></td>
            <td width="10%"><input type="button" width="100%" value="提交" onclick="show(<%=homework.getId()%>)"> </td>
        </tr>

        <%
                }
            }
        %>

    </table>
</form>
</body>
</html>