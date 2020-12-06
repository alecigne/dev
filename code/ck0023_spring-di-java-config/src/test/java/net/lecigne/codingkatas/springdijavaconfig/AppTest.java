package net.lecigne.codingkatas.springdijavaconfig;

import net.lecigne.codingkatas.springdijavaconfig.config.VehicleConfig;
import net.lecigne.codingkatas.springdijavaconfig.vehicle.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfig.class);

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    void test() {
        Vehicle vehicle = context.getBean(Vehicle.class);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.go()).isEqualTo("The driver is yelling: I drive as slow as possible.......\n" +
                "The sound the car makes is: Vroooom...");
    }

}
