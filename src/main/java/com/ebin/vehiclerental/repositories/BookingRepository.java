package com.ebin.vehiclerental.repositories;

import java.util.List;

import com.ebin.vehiclerental.entities.Booking;

public interface BookingRepository {

    public List<Booking> findByBranchIdAndWithinTime(String branchId, int startTime, int endTime);

    public List<Booking> findByBranchIdAndTypeAndWithinTime(String branchId, String vehicleType,
            int startTime, int endTime);

    public Booking save(Booking booking);
}
