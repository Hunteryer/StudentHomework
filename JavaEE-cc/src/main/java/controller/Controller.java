package controller;

import jdbc.StudentHomeworkJdbc;
import bean.Homework;
import bean.Student;
import bean.StudentHomework;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

//@Controller
public class Controller {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(jdbc.StudentHomeworkJdbc.class);
    StudentHomeworkJdbc jdbc = (StudentHomeworkJdbc) applicationContext.getBean("jdbc");

    @RequestMapping(path = "/showHomework", method = RequestMethod.GET)
    public ModelAndView showHomework(){

        List<Homework> list = jdbc.showHomework();

        return new ModelAndView("queryStudentHomework.jsp", "list", list);
    }


    @RequestMapping(path = "/allHomework", method = RequestMethod.GET)
    public ModelAndView allHomework(){
        //读取所有作业内容
        List<Homework> list = jdbc.showHomework();

        return new ModelAndView("queryAllHomework.jsp", "list", list);
    }

    @RequestMapping(path = "/query", method = RequestMethod.GET)
    public ModelAndView query(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String id = request.getParameter("id");
        List<StudentHomework> list = jdbc.selectAll(id);//访问数据库
        return new ModelAndView("homeworkSubmission.jsp", "list", list);
    }

    @RequestMapping(path = "/addHomework", method = RequestMethod.POST)
    public void addHomework(){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse resp = attributes.getResponse();

        try {
            request.setCharacterEncoding("utf-8");//设置编码，以防表单提交的内容乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Homework homework = new Homework();

        homework.setTitle(request.getParameter("title"));
        homework.setContent(request.getParameter("content"));
        Date date = new Date();
        homework.setCreateTime(date);

        boolean result = jdbc.addHomework(homework);

        request.setAttribute("isOK", result);    //用来判断是否添加作业成功
        request.setAttribute("type","addHomework");

        try {
            request.getRequestDispatcher("check.jsp").forward(request,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/addStudent", method = RequestMethod.POST)
    public void addStudent(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse resp = attributes.getResponse();

        try {
            request.setCharacterEncoding("utf-8");//设置编码，以防表单提交的内容乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Student student = new Student();
        student.setId(Long.parseLong(request.getParameter("id")));
        student.setName(request.getParameter("name"));
        Date date = new Date();
        student.setCreateTime(date);

        boolean result = jdbc.addStudent(student);

        request.setAttribute("isOK", result);  //用来判断是否添加作业成功
        request.setAttribute("type","addStudent");
        try {
            request.getRequestDispatcher("check.jsp").forward(request,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/willSubmit", method = RequestMethod.GET)
    public ModelAndView willSubmit(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String id = request.getParameter("id");

        //读取指定id的作业内容详细信息
        Homework homework = jdbc.showHomeworkDetails(id);//访问数据库
        return new ModelAndView("submitHomework.jsp", "homework", homework);
    }

    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public void submit(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        HttpServletResponse resp = attributes.getResponse();

        try {
            req.setCharacterEncoding("utf-8");//设置编码，以防表单提交的内容乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StudentHomework studentHomework = new StudentHomework();

        studentHomework.setStudentId(Long.parseLong(req.getParameter("studentId")));
        studentHomework.setHomeworkId(Long.parseLong(req.getParameter("homeworkId")));
        studentHomework.setHomeworkTitle(req.getParameter("title"));
        studentHomework.setHomeworkContent(req.getParameter("content"));
        Date date = new Date();
        studentHomework.setCreateTime(date);

        boolean result = jdbc.addStudentHomework(studentHomework);

        req.setAttribute("isOK", result);   //用于判断是否提交成功
        req.setAttribute("type","addStudentHomework");
        try {
            req.getRequestDispatcher("check.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}