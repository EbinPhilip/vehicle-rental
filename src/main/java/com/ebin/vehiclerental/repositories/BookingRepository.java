package com.ebin.vehiclerental.repositories;

import java.util.List;

import com.ebin.vehiclerental.entities.Booking;

public interface BookingRepository {

    public List<Booking> findByBranchId();

    public List<Booking> findByBranchIdAndVehicleType();

    public Booking save(Booking booking);
}
