package com.yang.springboot_shiro_jotanglogin.service;

import com.yang.springboot_shiro_jotanglogin.pojo.Role;
import com.yang.springboot_shiro_jotanglogin.pojo.User;

import java.util.List;

public interface UserService {
    //注册用户user的方法
    void register(User user);

    //根据账户查询用户user的方法
    User findByAccount(String account);

    //根据账户查询角色
    List<Role> findRolesByAccount(String account);
}
