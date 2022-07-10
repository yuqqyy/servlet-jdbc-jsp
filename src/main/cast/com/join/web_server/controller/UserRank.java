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
import java.util.List;

//排名
public class UserRank extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        try {
            List<User> list = userService.select();
            System.out.println("list.size"+list.size());
            req.setAttribute("userList",list);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("view/rank.jsp").forward(req,resp);
    }
}
