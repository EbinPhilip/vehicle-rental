package com.ebin.vehiclerental.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ebin.vehiclerental.entities.Booking;

public class BookingRepositoryImpl implements BookingRepository {

    private Map<UUID, Booking> bookingMap = new HashMap<>();

    @Override
    public List<Booking> findByBranchIdAndWithinTime(String branchId, int startTime, int endTime) {

        return bookingMap.values()
                .stream()
                .filter((i) -> {
                    return (i.getBranchId().equals(branchId) // match branch id
                            // check for time overlap
                            && ((i.getStartTime() >= startTime && i.getStartTime() < endTime)
                                    || (i.getEndTime() > startTime && i.getEndTime() <= endTime)));
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByBranchIdAndTypeAndWithinTime(String branchId, String vehicleType,
            int startTime, int endTime) {

        return bookingMap.values()
                .stream()
                .filter((i) -> {
                    return (i.getBranchId().equals(branchId) // match branch id
                            && i.getVehicleType().equals(vehicleType) // match vehicle type
                    // check for time overlap
                            && ((i.getStartTime() >= startTime && i.getStartTime() < endTime)
                                    || (i.getEndTime() > startTime && i.getEndTime() <= endTime)));
                })
                .collect(Collectors.toList());
    }

    @Override
    public Booking save(Booking booking) {

        bookingMap.put(booking.getBookingId(), booking);
        return booking;
    }
}
