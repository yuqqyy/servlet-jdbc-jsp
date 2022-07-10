package com.join.web_server.controller;

import com.join.web_server.service.UserService;
import com.join.web_server.service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//签到
public class SignIn extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("test/html;charset=UTF-8");
        //获取域对象内容
        String username = (String) req.getSession().getAttribute("username");
        System.out.println(username);
        LocalDateTime date = LocalDateTime.now();//签到时间
//        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String t1 = dataFormat.format(date);//字符串格式日期时间
        Long t1 = date.toInstant(ZoneOffset.of("+8")).toEpochMilli();//时间戳(毫秒)
        try {
            userService.update(username,t1,1);
            req.getRequestDispatcher("view/home.jsp").forward(req,resp);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }

    }
}
