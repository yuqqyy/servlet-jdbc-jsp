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

public class SelectDuration extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("test/html;charset=UTF-8");
        String username = req.getParameter("username");
        try {
            List<User> list = userService.select_list(username);
//            System.out.println(list.size());
            if(list.size() == 0){
                req.getRequestDispatcher("view/notFound.jsp").forward(req,resp);
            }else {
                req.setAttribute("userList",list);
                req.getRequestDispatcher("view/select.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
