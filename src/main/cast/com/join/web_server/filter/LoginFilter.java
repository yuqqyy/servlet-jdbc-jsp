package com.join.web_server.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        if (username == null || "".equals(username) || pwd == null || "".equals(pwd)){
            System.out.println("用户名或密码为空");
            req.getRequestDispatcher("view/error.jsp").forward(req,resp);
        }
        filterChain.doFilter(req, resp);
    }
    @Override
    public void destroy() {

    }
}
