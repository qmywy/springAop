package com.fly.view;

import com.fly.bean.Student;
import com.fly.service.StuBus;
import com.google.gson.Gson;
import com.fly.bean.Page;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class StuView extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private StuBus stuBus;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {

        ServletContext context = this.getServletContext();
        WebApplicationContext webApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(context);
        stuBus = webApplicationContext.getBean(StuBus.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.getSession().setAttribute("username","张三");
        PrintWriter printWriter = resp.getWriter();
        switch (id){

            case "1":

                Boolean b = stuBus.deleteStudent(2);
                printWriter.print(gson.toJson(b));
                break;

            case "2":

                Page<Student> page = stuBus.selectStudent(1,10);
                printWriter.print(gson.toJson(page));
                break;

            case "3":

                stuBus.testAopAround();
                System.out.print("开始测试");
                break;

        }

    }
}
