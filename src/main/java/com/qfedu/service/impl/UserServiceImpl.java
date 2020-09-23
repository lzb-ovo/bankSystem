package com.qfedu.service.impl;

import com.qfedu.dao.UserDao;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/3 15:21
 * description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void updateHeadImg(Integer id, String imgPath) {
        User u = new User();
        u.setId(id);
        u.setImgPath(imgPath);
        userDao.update(u);
    }

    @Override
    public User login(String bankCode, String password) {
        User user = userDao.findByCode(bankCode);
        if (user == null) {
            throw new RuntimeException("账号错误");
        }
        if (!user.getPassword().equals(password)){
            throw new RuntimeException("密码错误");
        }
        return user;
    }

    @Override
    public User selectByCode(String bankCode) {
        User user = userDao.findByCode(bankCode);
        return user;
    }

    @Override
    public void updatePassword(String oldName, String newName, String checkName, String backCode) {
        User u = userDao.findByCode(backCode);
        if (!oldName.equals(u.getPassword())){
//            System.out.println("原密码不正确");
            throw new RuntimeException("原密码不正确");
        }
        if(newName.equals(oldName)){
//            System.out.println("不能与原来的密码相同");
            throw new RuntimeException("不能与原来的密码相同");
        }
        if (newName.equals(checkName)){
//            System.out.println("两次密码输入不一样");
            throw new RuntimeException("两次密码输入不正确");
        }
        u.setPassword(newName);
        userDao.update(u);
    }

    @Override
    public void addBankCode(String bankCode, String password) {
        User user = new User();
        user.setBankCode(bankCode);
        user.setPassword(password);
        userDao.add(user);
    }

}
