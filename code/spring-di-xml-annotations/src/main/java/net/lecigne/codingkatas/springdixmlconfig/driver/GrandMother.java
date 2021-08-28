package net.lecigne.codingkatas.springdixmlconfig.driver;

import org.springframework.stereotype.Component;

@Component(value = "grandMother")
public class GrandMother implements Driver {

    @Override
    public String drive() {
        return "I drive as slow as possible.......";
    }

}
