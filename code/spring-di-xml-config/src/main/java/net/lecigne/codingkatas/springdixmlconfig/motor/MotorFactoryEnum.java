package net.lecigne.codingkatas.springdixmlconfig.motor;

import java.util.function.Supplier;

public class MotorFactoryEnum {

    public enum MotorEnum {
        GAS_ENGINE(GasEngine::new),
        ELECTRIC_MOTOR(ElectricMotor::new);

        private final Supplier<Motor> instantiator;

        MotorEnum(Supplier<Motor> instantiator) {
            this.instantiator = instantiator;
        }

        public Motor getInstance() {
            return instantiator.get();
        }
    }

    public static Motor getMotor(MotorEnum motorEnum) {
        return motorEnum.getInstance();
    }

}
