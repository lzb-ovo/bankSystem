package com.qfedu.controller;

import com.qfedu.entity.User;
import com.qfedu.utils.JsonUtils;

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
 * time: 2020/9/4 11:14
 * description:
 */
//@WebServlet("/user/query.do")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            JsonUtils.writeJsonInfo(1,loginUser.getBankCode(),resp);
        }else {
            JsonUtils.writeJsonInfo(0,null,resp);
        }
    }
}
