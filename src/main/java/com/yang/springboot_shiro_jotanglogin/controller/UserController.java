package com.yang.springboot_shiro_jotanglogin.controller;

import com.yang.springboot_shiro_jotanglogin.pojo.User;
import com.yang.springboot_shiro_jotanglogin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject; //注意：导入shiro下的Subject！
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/*
controller层,调用service层
 */
@Controller
public class UserController {
    @Autowired //自动装配
    private UserService userService;

    /*
   设置默认欢迎页为登录页
    */
    @RequestMapping("/")
    public String view(){
        return "redirect:/login.jsp";
    }


    /*
    注册
     */
    @RequestMapping("register")
    public String register(User user) {
        try {
            userService.register(user);
            //注册成功，重定向到登录页面
            return "redirect:/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            //注册失败，重定向到注册页面
            return "redirect:/register.jsp";
        }
    }

    /*
    登录
     */
    @RequestMapping("login")
    public String login(String account,String password){
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        try {
            //使用shiro中的subject.login登录、认证！
            subject.login(new UsernamePasswordToken(account,password));
            return  "redirect:/success.jsp";
        } catch (UnknownAccountException e) {
            System.out.println("未注册");
            //登录失败，重定向到登录页面
            e.printStackTrace();
            return "redirect:/login.jsp";
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            e.printStackTrace();
            //登录失败，重定向到登录页面
            return "redirect:/login.jsp";
        }
    }


}
