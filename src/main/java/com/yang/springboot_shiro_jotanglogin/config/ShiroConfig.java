package com.yang.springboot_shiro_jotanglogin.config;

import com.yang.springboot_shiro_jotanglogin.shiro.CustomRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import java.util.HashMap;
import java.util.Map;

/**
 * shiro的配置类
 */
@Configuration
public class ShiroConfig {
    /*
    创建shiroFilter 过滤器：
        拦截请求，认证授权后才可以访问受限资源
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //设置公共资源、受限资源
        Map<String,String> authMap = new HashMap<String,String>();
        authMap.put("/login","anon");//anon->公共资源
        authMap.put("/login.jsp","anon");
        authMap.put("/register","anon");
        authMap.put("/register.jsp","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(authMap);

        //设置认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        return shiroFilterFactoryBean;
    }

    /*
    创建安全管理器（固定代码）
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    /*
    创建自定义realm
        并配置凭证匹配器，
     */
    @Bean
    public Realm getRealm(){
        CustomRealm customRealm = new CustomRealm();

        /*
        配置凭证匹配器，实现MD5+Salt+Hash散列进行密码加密
         */
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //加密算法设为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customRealm.setCredentialsMatcher(credentialsMatcher);

        return customRealm;
    }

}
