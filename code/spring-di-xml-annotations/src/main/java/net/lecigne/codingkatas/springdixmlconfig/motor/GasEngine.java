package net.lecigne.codingkatas.springdixmlconfig.motor;

import org.springframework.stereotype.Component;

@Component(value = "gasEngine")
public class GasEngine implements Motor {

    @Override
    public String propel() {
        return "Vroooom...";
    }

}
