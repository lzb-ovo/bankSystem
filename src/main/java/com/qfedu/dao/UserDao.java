package com.qfedu.dao;

import com.qfedu.entity.User;

/**
 * projectName: bankSystem
 *
 * @author: 罗智博
 * time: 2020/9/3 15:22
 * description:
 */
public interface UserDao {
    public User findByCode(String bankCode);

    public void update(User u);

    public void add(User user);
}
