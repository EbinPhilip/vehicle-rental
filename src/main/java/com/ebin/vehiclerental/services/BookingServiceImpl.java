package com.ebin.vehiclerental.services;

import java.util.List;
import java.util.UUID;

import com.ebin.vehiclerental.entities.Booking;
import com.ebin.vehiclerental.entities.Vehicle;
import com.ebin.vehiclerental.exceptions.InvalidTimeSlotException;
import com.ebin.vehiclerental.repositories.BookingRepository;
import com.ebin.vehiclerental.utils.bookingstrategy.BookingStrategy;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    @NonNull
    private BookingRepository bookingRepository;

    @NonNull
    private VehicleAvailabilityService vehicleService;

    @NonNull
    private BookingStrategy bookingStrategy;

    @Override
    public double bookVehicle(String branchId, String vehicleType, int startTime, int endTime) {

        if (startTime >= endTime || startTime < 1 || startTime > 23 || endTime < 2 || endTime > 24) {
            throw new InvalidTimeSlotException();
        }

        List<Vehicle> vehicles = vehicleService.getAvailableByBranchAndVehicleType(
                branchId, vehicleType, startTime, endTime);

        // Get the vehicle to book using bookingstrategy
        Vehicle vehicleToBook = bookingStrategy.getVehicleToBook(vehicles);
        // Calculate price, create booking and save it to repository
        double finalPrice = (endTime - startTime) * vehicleToBook.getPrice();
        Booking booking = new Booking(UUID.randomUUID(), vehicleToBook.getVehicleId(),
                vehicleType, branchId, startTime, endTime, finalPrice);
        bookingRepository.save(booking);

        return booking.getPrice();
    }
}
