package net.lecigne.codingkatas.springdijavaconfig.config;

import net.lecigne.codingkatas.springdijavaconfig.driver.CrazyDriver;
import net.lecigne.codingkatas.springdijavaconfig.driver.Driver;
import net.lecigne.codingkatas.springdijavaconfig.driver.GrandMother;
import net.lecigne.codingkatas.springdijavaconfig.motor.ElectricMotor;
import net.lecigne.codingkatas.springdijavaconfig.motor.GasEngine;
import net.lecigne.codingkatas.springdijavaconfig.motor.Motor;
import net.lecigne.codingkatas.springdijavaconfig.vehicle.Vehicle;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class VehicleConfig {

    @Bean
    public Driver crazyDriver() {
        return new CrazyDriver();
    }

    @Bean
    public Driver grandMother() {
        return new GrandMother();
    }

    @Bean
    public Motor electricMotor() {
        return new ElectricMotor();
    }

    @Bean
    public Motor gasEngine() {
        return new GasEngine();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Vehicle vehicle() {
        return new Vehicle(gasEngine(), grandMother());
    }

}
