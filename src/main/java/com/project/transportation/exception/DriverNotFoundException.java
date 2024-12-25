package com.project.transportation.exception;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String driverNotFound) {
        super(driverNotFound);
    }
}
