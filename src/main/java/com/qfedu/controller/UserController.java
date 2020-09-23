package com.qfedu.controller;


import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.service.impl.UserServiceImpl;
import com.qfedu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/query.do")
    @ResponseBody
    public JsonResult getLoginInfo (HttpSession session) {
        User u = (User)session.getAttribute(StrUtils.LOGIN_USER);
        User user = userService.selectByCode(u.getBankCode());
        JsonResult jsonResult = new JsonResult(1, user);
        return jsonResult;
    }

    @RequestMapping("/exit.do")
    @ResponseBody
    public JsonResult exit(HttpSession session, HttpServletResponse response){
        session.removeAttribute(StrUtils.LOGIN_USER);
        JsonResult jsonResult = new JsonResult(1, "退出成功");
        return jsonResult;
    }
    @RequestMapping("/change.do")
    @ResponseBody
    public JsonResult change(String oldName,String newName,String checkName,HttpSession session){
        User u = (User) session.getAttribute(StrUtils.LOGIN_USER);
        String bankCode = u.getBankCode();
        userService.updatePassword(oldName,newName,checkName,bankCode);
        JsonResult jsonResult = new JsonResult(1, "修改成功");
        return jsonResult;
    }

    @RequestMapping("/register.do")
    @ResponseBody
    public JsonResult change(String bankCode,String password){
        userService.addBankCode(bankCode,password);
        JsonResult jsonResult = new JsonResult(1, "注册成功即将返回登录");
        return jsonResult;
    }
}
