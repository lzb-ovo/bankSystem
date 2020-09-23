package com.qfedu.interceptor;

import com.qfedu.entity.User;
import com.qfedu.utils.StrUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/11 13:02
 * description:拦截器 如果session有值则自动登录
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获得资源的标识符
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        User user = (User) request.getSession().getAttribute(StrUtils.LOGIN_USER);
        if (user == null) {//证明没有登录需要跳转到login.html
            //如果是ajaxq请求也得跳转到login.html
            //如果是jquery的ajax请求的话，浏览器会带有一个特殊的请求头
            String value = request.getHeader("X-Request-With");
            //ajax请求是必须返回json数据来处理的
            if (value != null&&value.equals("XMLHttpRequest")) {
                response.getWriter().write("{\"code\":0, \"info\":\"未登录\"}");
            }else {
                //普通页面直接跳转
                response.sendRedirect(request.getContextPath()+"/login.html");
            }
            return false;
        }
        return true;
    }
}
