package com.victorzhenwang.statemachinedemo;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mapping.Alias;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration({"classpath:config/server.xml"})
public abstract class BaseTest {

    protected static ApplicationContext appContext;

    @BeforeClass
    public static void setUp() {
        try {
            Class<?> clazz = BaseTest.class;
            ContextConfiguration declaredAnnotation = clazz.getAnnotation(ContextConfiguration.class);
            String[] classpaths = declaredAnnotation.value();
            appContext = new ClassPathXmlApplicationContext(classpaths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Before
    public void printBeans(){
        String[] beans = appContext.getBeanDefinitionNames();
        System.out.println(beans.length);
//		for (String beanName : beans) {
//			System.out.println("BeanName:" + beanName);
//		}
    }

//    @Before
//    public void autoSetBean() {
//        appContext.
//                getAutowireCapableBeanFactory().
//                autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
//    }

}
