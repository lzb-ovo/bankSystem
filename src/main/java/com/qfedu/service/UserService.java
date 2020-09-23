package com.qfedu.service;

import com.qfedu.entity.User;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/3 15:21
 * description:
 */
public interface UserService {
    public void updateHeadImg(Integer id, String imgPath);

    public User login(String bankCode, String password);

    public User selectByCode(String bankCode);

    public void updatePassword(String oldName, String newName, String checkName, String bankCode);

    public void addBankCode(String bankCode, String password);
}
