package com.victorzhenwang.statemachinedemo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void printAllBeans() {
        ApplicationContext ctx = SpringContextHolder.getApplicationContext();
        String[] beans = ctx.getBeanDefinitionNames();
        for (String beanName : beans) {
            Class<?> beanType = SpringContextHolder.getApplicationContext()
                    .getType(beanName);
            System.out.println("BeanName:" + beanName);
            System.out.println("Bean type：" + beanType);
            System.out.println("Bean package：" + beanType.getPackage());
            System.out.println("Bean：" + SpringContextHolder.getApplicationContext().getBean(
                    beanName));
        }
    }

}
