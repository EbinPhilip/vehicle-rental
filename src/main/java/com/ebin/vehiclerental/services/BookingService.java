package com.ebin.vehiclerental.services;

import java.util.List;

public interface BookingService {

    public List<String> getAvailableVehicles(String branchId, int startTime, int endTime);

    public double bookVehicle(String branchId, String vehicleType, int startTime, int endTime);
}
