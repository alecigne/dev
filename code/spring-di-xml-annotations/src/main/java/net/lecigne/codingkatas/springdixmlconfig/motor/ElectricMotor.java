package net.lecigne.codingkatas.springdixmlconfig.motor;

import org.springframework.stereotype.Component;

@Component(value = "electricMotor")
public class ElectricMotor implements Motor {

    @Override
    public String propel() {
        return "Bzzzz...";
    }

}
