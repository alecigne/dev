package net.lecigne.katas.springlifecycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("java:S106")
public class Application {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Book book = (Book) context.getBean("bookBean");
        System.out.println("--> [STEP: BEAN READY] getBookName() (from the Book bean)");
        System.out.println("Book name: " + book.getBookName());
        context.registerShutdownHook();
   }

}


