package com.ebin.vehiclerental.exceptions;

public class InvalidVehicleTypeException extends RuntimeException {
    public InvalidVehicleTypeException()
    {
     super();
    }
    public InvalidVehicleTypeException(String msg)
    {
     super(msg);
    }
}