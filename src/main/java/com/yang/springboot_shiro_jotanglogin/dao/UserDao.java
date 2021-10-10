package com.yang.springboot_shiro_jotanglogin.dao;

import com.yang.springboot_shiro_jotanglogin.pojo.Role;
import com.yang.springboot_shiro_jotanglogin.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    //注册保存用户的方法
    void saveUser(User user);
    //根据查询账号找用户
    User findByAccount(String account);
    //根据账号查询对应用户的所有角色
    List<Role> findRolesByAccount(String account);
}
