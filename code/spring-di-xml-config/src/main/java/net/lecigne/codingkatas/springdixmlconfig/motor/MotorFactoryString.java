package net.lecigne.codingkatas.springdixmlconfig.motor;

public class MotorFactoryString {

    public static Motor getMotor(String motorType) {
        switch (motorType) {
            case "gas":
                return new GasEngine();
            case "electric":
                return new ElectricMotor();
            default:
                throw new IllegalArgumentException();
        }
    }

}
