package com.ebin.vehiclerental.services;

public interface BookingService {

    public double bookVehicle(String branchId, String vehicleType, int startTime, int endTime);
}
