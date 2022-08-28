package com.ebin.vehiclerental.exceptions;

public class InvalidTimeSlotException extends RuntimeException {
    public InvalidTimeSlotException()
    {
     super();
    }
    public InvalidTimeSlotException(String msg)
    {
     super(msg);
    }
}