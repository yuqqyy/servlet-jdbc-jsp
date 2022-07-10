package com.join.web_server.controller;

import com.join.web_server.entity.User;
import com.join.web_server.service.UserService;
import com.join.web_server.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

//登录
public class UserLogin extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        try {
            User user = userService.login(username,pwd);
            if (!Objects.isNull(user)) {
                HttpSession session = req.getSession();
                //设置session域对象和req域对象
                session.setAttribute("user",user);
                session.setAttribute("username",username);
                req.setAttribute("session",session);
                req.setAttribute("username",username);
                req.getRequestDispatcher("view/home.jsp").forward(req,resp);
            } else {
                resp.getWriter().println("用户名或密码错误");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("login"+e);
        }
    }
}
