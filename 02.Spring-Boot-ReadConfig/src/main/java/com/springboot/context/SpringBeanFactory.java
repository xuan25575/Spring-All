package com.springboot.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// 这个类一定加入Spring工厂
@Component
public class SpringBeanFactory implements ApplicationContextAware {


    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.applicationContext = applicationContext;
    }


    /**
     * 范型方法
     * 获取容器bean实例.
     * @param beanName beam 名称。
     * @param tClass 类型class
     * @param <T>  返回值类型 T
     * @return  返回值
     */
    public static <T> T getBean(String beanName,Class<T> tClass){
        return applicationContext.getBean(beanName,tClass);
    }


    /**
     * @return the applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
