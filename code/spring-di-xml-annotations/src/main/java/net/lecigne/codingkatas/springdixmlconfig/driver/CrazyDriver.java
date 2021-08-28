package net.lecigne.codingkatas.springdixmlconfig.driver;

import org.springframework.stereotype.Component;

@Component(value = "crazyDriver")
public class CrazyDriver implements Driver {

    @Override
    public String drive() {
        return "I drive very fast!!!";
    }

}
