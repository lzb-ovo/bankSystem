package com.qfedu.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/10 21:46
 * description:处理全局异常的类
 */
@ControllerAdvice//控制器增强 也必须扫描 不然这个注解不生效
@ResponseBody
public class CommonException {
    @ExceptionHandler(ConstraintViolationException.class)//修饰一个处理异常的方法
    public JsonResult constraintViolationException(ConstraintViolationException ex){
        Iterator<ConstraintViolation<?>> iterator = ex.getConstraintViolations().iterator();
        String message = null;
        if (iterator.hasNext()) {
            message = iterator.next().getMessage();
            System.out.println("message = " + message);
        }
        return new JsonResult(0,message);
    }
    @ExceptionHandler
    public JsonResult commonException(Exception ex){
        return new JsonResult(0,ex.getMessage());
    }
}
