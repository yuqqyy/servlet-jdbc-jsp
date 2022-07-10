package com.join.web_server.controller;

import com.join.web_server.entity.User;
import com.join.web_server.service.UserService;
import com.join.web_server.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//签退并更新时长

public class SignOut extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取域对象内容
        String username = (String) req.getSession().getAttribute("username");
        System.out.println(username);
        Long t2 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        try {
            userService.update(username,t2);
            User user = userService.select(username);
            Long t1 = user.getSignInTime();
            Calculate calculate=new Calculate();
            Long duration = calculate.calc(t1,t2);
            duration += user.getDuration();//加上表中原有时长
            userService.update(duration,username);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            req.getSession().removeAttribute("username");//销毁session
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
