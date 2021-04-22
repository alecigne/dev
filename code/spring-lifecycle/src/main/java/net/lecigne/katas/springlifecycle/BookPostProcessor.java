package net.lecigne.katas.springlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@SuppressWarnings("java:S106")
public class BookPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("--> [STEP: PREINIT] BeanPostProcessor.postProcessBeforeInitialization()");
        System.out.println("Bean Name " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("--> [STEP: POSTINIT] BeanPostProcessor.postProcessAfterInitialization()");
        System.out.println("Bean Name " + beanName);
        return bean;
    }
}
