package com.yang.springboot_shiro_jotanglogin.shiro;

import com.yang.springboot_shiro_jotanglogin.pojo.Role;
import com.yang.springboot_shiro_jotanglogin.pojo.User;
import com.yang.springboot_shiro_jotanglogin.service.UserService;
import com.yang.springboot_shiro_jotanglogin.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


import java.util.List;


/*
自定义realm
 */
public class CustomRealm extends AuthorizingRealm {
    /*
    认证
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        //根据登录者的身份信息从数据库查找用户
        String principal = (String) token.getPrincipal();
        User user = userService.findByAccount(principal);
        if(user!=null){
            //SimpleAuthenticationInfo(): 传入的password和token（filter中登录时自动生成的）中的password做对比，（传入盐就加盐后对比）
            // 如果相同就允许登录，不相同就抛出异常
            // 如果验证成功，返回值是传入的第一个字段的值
            return new SimpleAuthenticationInfo(
                    user.getAccount(),
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),
                    this.getName());
        }
        return null;
    }

    /*
    授权：
        认证通过的用户用这个方法拿到权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        //根据主身份信息从数据库获取角色
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        List<Role> roles = userService.findRolesByAccount(primaryPrincipal);
        //授权角色信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if(roles.get(0)!=null){
//            roles.forEach(role->{
//                simpleAuthorizationInfo.addRole(role.getName());
//            });
            for(Role role: roles){
                simpleAuthorizationInfo.addRole(role.getName());
            }
        }else {
            //默认角色是user
            simpleAuthorizationInfo.addRole("user");
        }
        return simpleAuthorizationInfo;
    }

}
