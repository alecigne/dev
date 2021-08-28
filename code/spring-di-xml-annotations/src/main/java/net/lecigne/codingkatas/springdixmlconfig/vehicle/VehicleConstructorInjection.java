package net.lecigne.codingkatas.springdixmlconfig.vehicle;

import net.lecigne.codingkatas.springdixmlconfig.driver.Driver;
import net.lecigne.codingkatas.springdixmlconfig.motor.Motor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class VehicleConstructorInjection {

    private final Motor motor;
    private final Driver driver;

    @Autowired
    public VehicleConstructorInjection(@Qualifier("gasEngine") Motor motor,
                                       @Qualifier("grandMother") Driver driver) {
        this.motor = motor;
        this.driver = driver;
    }

    public String go() {
        return "The driver is yelling: " + driver.drive() + "\n" +
                "The sound the car makes is: " + motor.propel();
    }

}
