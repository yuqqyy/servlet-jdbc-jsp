package com.join.web_server.controller;

import com.join.web_server.entity.User;
import com.join.web_server.service.UserService;
import com.join.web_server.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

public class AllServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.lastIndexOf("."));
        try{
            switch (methodName){
                case "userLogin": userLogin(req,resp);
                case "userRank": userRank(req,resp);
                case "userSign": userSign(req,resp);
                case "signIn": signIn(req,resp);
                case "signOut": signOut(req,resp);
                case "selectDuration": selectDuration(req,resp);
            }
            req.getRequestDispatcher("view/methodError.jsp").forward(req,resp);
        }catch (Exception e){
            throw new RuntimeException("调用方法出错");
        }
    }

    private void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void userRank(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<User> list = userService.select();
            System.out.println("list.size"+list.size());
            req.setAttribute("userList",list);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("view/rank.jsp").forward(req,resp);
    }

    private void userSign(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = (String) req.getSession().getAttribute("username");
        System.out.println(username);
        LocalDateTime date = LocalDateTime.now();//签到时间
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

    private void signOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    private void selectDuration(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        try {
            List<User> list = userService.select_list(username);
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
