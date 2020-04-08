package servlet;

import jdbc.StudentHomeworkJdbc;
import bean.Homework;
import bean.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/submit")
public class SubmitHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Homework homework = StudentHomeworkJdbc.showHomeworkDetails(id);//访问数据库
        req.setAttribute("homework",homework);
        req.getRequestDispatcher("submitHomework.jsp").forward(req,resp); //展示内容
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");  //设置统一字符编码

        StudentHomework studentHomework = new StudentHomework();

        studentHomework.setStudentId(Long.parseLong(req.getParameter("studentId")));
        studentHomework.setHomeworkId(Long.parseLong(req.getParameter("homeworkId")));
        studentHomework.setHomeworkTitle(req.getParameter("title"));
        studentHomework.setHomeworkContent(req.getParameter("content"));
        Date date = new Date();
        studentHomework.setCreateTime(date);

        boolean result = StudentHomeworkJdbc.addStudentHomework(studentHomework);

        req.setAttribute("isOK", result);   //判断是否提交成功
        req.setAttribute("type","addStudentHomework");
        req.getRequestDispatcher("check.jsp").forward(req,resp);
    }
}
