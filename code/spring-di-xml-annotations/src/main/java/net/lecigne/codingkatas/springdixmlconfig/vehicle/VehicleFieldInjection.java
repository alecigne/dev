package net.lecigne.codingkatas.springdixmlconfig.vehicle;

import net.lecigne.codingkatas.springdixmlconfig.driver.Driver;
import net.lecigne.codingkatas.springdixmlconfig.motor.Motor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

// Bean definition in beans.xml
public class VehicleFieldInjection {

    @Autowired
    @Qualifier("electricMotor")
    private Motor motor;

    @Autowired
    @Qualifier("grandMother")
    private Driver driver;

    public String go() {
        return "The driver is yelling: " + driver.drive() + "\n" +
                "The sound the car makes is: " + motor.propel();
    }

}
