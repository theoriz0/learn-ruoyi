package cn.theoriz.ruoyi.common.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public final class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {
    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext;
    public static <T> T getBean(Class<T> clz) throws BeansException {
        return (T) beanFactory.getBean(clz);
    }

    public static <T> T getBean(String beanName) throws BeansException {
        return (T) beanFactory.getBean(beanName);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }
}
