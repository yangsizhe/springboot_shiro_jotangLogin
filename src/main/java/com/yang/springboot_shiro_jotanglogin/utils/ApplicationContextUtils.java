package com.yang.springboot_shiro_jotanglogin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component //声明是组件类，告知Spring为这个类创建bean。
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    //根据名字获取工厂中bean对象
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
}
