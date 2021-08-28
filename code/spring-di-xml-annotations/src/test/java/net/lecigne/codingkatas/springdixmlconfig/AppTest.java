package net.lecigne.codingkatas.springdixmlconfig;

import net.lecigne.codingkatas.springdixmlconfig.vehicle.VehicleConstructorInjection;
import net.lecigne.codingkatas.springdixmlconfig.vehicle.VehicleFieldInjection;
import net.lecigne.codingkatas.springdixmlconfig.vehicle.VehicleSetterInjection;
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

    /**
     * Constructor injection: grandmother and gas engine
     */
    @Test
    void injectionByConstructor_shouldWork() {
        VehicleConstructorInjection vehicle = context.getBean("vehicleConstructorInjection", VehicleConstructorInjection.class);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.go()).isEqualTo("The driver is yelling: I drive as slow as possible......." +
                "\n" + "The sound the car makes is: Vroooom...");
    }

    /**
     * Setter injection: crazy driver and electric motor
     */
    @Test
    void injectionBySetter_shouldWork() {
        VehicleSetterInjection vehicle = context.getBean("vehicleSetterInjection", VehicleSetterInjection.class);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.go()).isEqualTo("The driver is yelling: I drive very fast!!!" +
                        "\n" + "The sound the car makes is: Bzzzz...");
    }

    /**
     * Field injection: grandmother and electric motor
     */
    @Test
    void injectionByField_shouldWork() {
        VehicleFieldInjection vehicle = context.getBean("vehicleFieldInjection", VehicleFieldInjection.class);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.go()).isEqualTo("The driver is yelling: I drive as slow as possible......." +
                "\n" + "The sound the car makes is: Bzzzz...");
    }

}
