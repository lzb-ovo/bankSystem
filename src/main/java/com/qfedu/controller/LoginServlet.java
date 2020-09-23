package com.qfedu.controller;

import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.service.impl.UserServiceImpl;
import com.qfedu.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/3 15:20
 * description:
 */
//@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    @Autowired
    private UserService userService;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String bankCode = req.getParameter("bankCode");
        String password = req.getParameter("password");

        try {
            User user = userService.login(bankCode, password);
            HttpSession session = req.getSession();
            session.setAttribute("loginUser",user);
            JsonUtils.writeJsonInfo(1,null,resp);
        }catch (Exception e){
            e.printStackTrace();
            JsonUtils.writeJsonInfo(0,e.getMessage(),resp);
        }
    }
}
