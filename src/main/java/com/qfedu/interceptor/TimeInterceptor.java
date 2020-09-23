package com.qfedu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/11 21:47
 * description:
 */

public class TimeInterceptor implements HandlerInterceptor {
    private ThreadLocal<Long> threadLocal;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("dz");
        long begin = System.currentTimeMillis();
        threadLocal.set(begin);
        Thread.sleep(5000);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("shuai");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("lzb");
        long end = System.currentTimeMillis();
        Long begin = threadLocal.get();
        long v = end - begin;
        System.out.println("v = " + v);
    }
}
