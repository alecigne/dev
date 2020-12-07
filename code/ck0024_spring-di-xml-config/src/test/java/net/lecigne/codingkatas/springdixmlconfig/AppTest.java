package net.lecigne.codingkatas.springdixmlconfig;

import net.lecigne.codingkatas.springdixmlconfig.vehicle.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    void injectionByConstructor_shouldWork() {
        Vehicle vehicle = context.getBean("vehicleWithConstructorInjection", Vehicle.class);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.go()).isEqualTo("The driver is yelling: I drive as slow as possible......." +
                "\n" + "The sound the car makes is: Vroooom...");
    }

    @Test
    void injectionBySetter_shouldWork() {
        Vehicle vehicleSetter = context.getBean("vehicleWithSetterInjection", Vehicle.class);
        assertThat(vehicleSetter).isNotNull();
        assertThat(vehicleSetter.go()).isEqualTo("The driver is yelling: I drive very fast!!!" +
                        "\n" + "The sound the car makes is: Bzzzz...");
    }

    @Test
    void injectionByConstructor_withBeanFromStringFactory_shouldWork() {
        Vehicle vehicleFactoryString = context.getBean("vehicleFactoryString", Vehicle.class);
        assertThat(vehicleFactoryString).isNotNull();
        assertThat(vehicleFactoryString.go()).isEqualTo("The driver is yelling: I drive as slow as possible......." +
                "\n" + "The sound the car makes is: Bzzzz...");
    }

    @Test
    void injectionByConstructor_withBeanFromEnumFactory_shouldWork() {
        Vehicle vehicleFactoryEnum = context.getBean("vehicleFactoryEnum", Vehicle.class);
        assertThat(vehicleFactoryEnum).isNotNull();
        assertThat(vehicleFactoryEnum.go()).isEqualTo("The driver is yelling: I drive as slow as possible......." +
                "\n" + "The sound the car makes is: Vroooom...");
    }

}
