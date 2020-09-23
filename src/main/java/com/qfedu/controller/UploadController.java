package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/10 22:40
 * description:
 */
@Controller
public class UploadController {
    @Autowired
    private UserService userService;
    @RequestMapping("/upload.do")
    @ResponseBody
    /**
     * 上传文件对应的方法的参数类型是@RequestParam MultipartFile
     */
    public JsonResult uploadFile(@RequestParam MultipartFile file, HttpSession session){
        User u = (User) session.getAttribute(StrUtils.LOGIN_USER);
        if (u == null) {
            return new JsonResult(0,"未登录");
        }
        //0把文件写到本地磁盘里面，刚好对应设置好的虚拟路径 c:/upload
        //1上传文件的本地磁盘目录
        String dir = "E:/upload";
        //2获取上传文件的名字
        String filename = file.getOriginalFilename();
        System.out.println("filename = " + filename);
        //3判断上传文件的目录是否存在
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            //如果不存在则创建一个新的文件夹
            dirFile.mkdirs();
        }
        //4把上传的文件保存成一个对象
        File newFile = new File(dir, filename);
        //5保存文件
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService.updateHeadImg(u.getId(), "/upload/"+ filename);
        return new JsonResult(1,"上传成功");
    }
}
