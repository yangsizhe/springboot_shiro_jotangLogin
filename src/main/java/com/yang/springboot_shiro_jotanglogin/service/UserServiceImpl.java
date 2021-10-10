package com.yang.springboot_shiro_jotanglogin.service;

import com.yang.springboot_shiro_jotanglogin.dao.UserDao;
import com.yang.springboot_shiro_jotanglogin.pojo.Role;
import com.yang.springboot_shiro_jotanglogin.pojo.User;
import com.yang.springboot_shiro_jotanglogin.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
service层调用dao层
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public List<Role> findRolesByAccount(String account) {
        return userDao.findRolesByAccount(account);
    }

    @Override
    public User findByAccount(String account) {
        return userDao.findByAccount(account);
    }

    @Override
    public void register(User user) {
        //处理业务调用dao
        //1.生成随机盐
        String salt = SaltUtils.generateSalt(user.getAccount());
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userDao.saveUser(user);
    }
}
