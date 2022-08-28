package com.ebin.vehiclerental.services;

import java.util.List;
import java.util.UUID;

import com.ebin.vehiclerental.entities.Booking;
import com.ebin.vehiclerental.entities.Vehicle;
import com.ebin.vehiclerental.repositories.BookingRepository;
import com.ebin.vehiclerental.utils.bookingstrategy.BookingStrategy;

public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    private VehicleService vehicleService;

    private BookingStrategy bookingStrategy;


    public double bookVehicle(String branchId, String vehicleType, int startTime, int endTime) {

        List<Vehicle> vehicles = vehicleService.getAvailableByBranchAndVehicleType(
                branchId, vehicleType, startTime, endTime);

        // Get the vehicle to book using bookingstrategy. Save it to repository
        Vehicle vehicleToBook = bookingStrategy.getVehicleToBook(vehicles);
        Booking booking = new Booking(UUID.randomUUID(), vehicleToBook.getVehicleId(),
                vehicleType, branchId, startTime, endTime, vehicleToBook.getPrice());
        bookingRepository.save(booking);
    
        return vehicleToBook.getPrice();
    }
}
