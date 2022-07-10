package com.join.web_server.controller;

import com.join.web_server.service.UserService;
import com.join.web_server.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;

//注册
@MultipartConfig//文件上传注解
public class UserSign extends HttpServlet {
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
        String pwd = req.getParameter("pwd");
        String check_pwd = req.getParameter("check_pwd");
        String profession = req.getParameter("profession");
        //头像上传
        Part part = req.getPart("picture");
        String fileName = part.getSubmittedFileName();//获取文件名
        String realPath = req.getServletContext().getRealPath("/");//获取文件存储的绝对路径
        part.write(realPath + fileName);
        try {
            if(pwd.equals(check_pwd)) {
                userService.sign(username, pwd, profession, fileName);
                req.getRequestDispatcher("index.jsp").forward(req, resp);//跳转到登录界面
            }else{
                req.getRequestDispatcher("view/signError.jsp").forward(req, resp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
