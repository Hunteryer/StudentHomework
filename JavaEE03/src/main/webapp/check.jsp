<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/3/11
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check</title>
</head>
<body>
<%
    String type = (String)request.getAttribute("type");
    boolean isOK = (boolean)request.getAttribute("isOK");
    if (type.equals("addHomework")){

        if (isOK){
%>

<p align="center" style="margin: 50px; font-family: Arial; font-size: 30px; color: blue"><%="添加成功!"%></p>

<%
}else{
%>

<p align="center" style="margin: 50px; font-family: Arial; font-size: 30px;color: red"><%="添加失败!"%></p>

<%
    }
}else if (type.equals("addStudent")){
    if (isOK){
%>

<p align="center" style="margin: 50px; font-family: Arial; font-size: 30px; color: blue"><%="添加成功!"%></p>

<%
}else{
%>

<p align="center" style="margin: 50px; font-family: Arial; font-size: 30px;color: red"><%="已有学生信息!无法添加！"%></p>

<%
    }
}else if (type.equals("addStudentHomework")){
    if (isOK){
%>

<p align="center" style="margin: 50px; font-family: Arial; font-size: 30px; color: blue"><%="提交成功!"%></p>

<%
}else {
%>

<p align="center" style="margin: 50px; font-family: Arial; font-size: 30px; color: red"><%="提交失败!"%></p>

<%
        }
    }
%>
<p align="center">
    <a href="index.jsp" style="margin: 65px">确认</a></p>
</body>
</html>
