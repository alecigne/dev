package net.lecigne.katas.springlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

@SuppressWarnings("java:S106")
public class Book implements InitializingBean, DisposableBean, ApplicationContextAware, BeanNameAware, BeanFactoryAware {
    private String bookName;

    public Book() {
        System.out.println("--> [STEP: INSTANTIATION] Book()");
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("--> [STEP: INIT-2] InitializingBean.afterPropertiesSet()");
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("--> [STEP: INIT-1] postConstruct() (@PostConstruct annotation)");
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("--> [STEP: DESTROY-1] preDestroy() (@PreDestroy annotation)");
    }

    @Override
    public void destroy() {
        System.out.println("--> [STEP: DESTROY-2] DisposableBean.destroy()");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("--> [STEP: AWARE INTERFACES] ApplicationContextAware.setApplicationContext()");
        System.out.println("Bean definition names -> " + Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("--> [STEP: AWARE INTERFACES] BeanNameAware.setBeanName()");
        System.out.println("Bean name -> " + beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("--> [STEP: AWARE INTERFACES] BeanFactoryAware.setBeanFactory()");
        System.out.println("Singleton -> " + beanFactory.isSingleton("bookBean"));
    }

    @SuppressWarnings("unused") // beans.xml
    private void customInit() {
        System.out.println("--> [STEP: INIT-3] customInit() (from beans.xml)");
    }

    @SuppressWarnings("unused") // beans.xml
    private void customDestroy() {
        System.out.println("--> [STEP: DESTROY-3] customDestroy() (from beans.xml)");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                '}';
    }
}
