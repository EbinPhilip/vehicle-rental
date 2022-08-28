package com.ebin.vehiclerental.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ebin.vehiclerental.entities.Booking;
import com.ebin.vehiclerental.entities.Branch;
import com.ebin.vehiclerental.entities.Vehicle;
import com.ebin.vehiclerental.exceptions.InvalidVehicleTypeException;
import com.ebin.vehiclerental.repositories.BookingRepository;
import com.ebin.vehiclerental.repositories.VehicleRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    @NonNull
    private BranchService branchService;

    @NonNull
    private BookingRepository bookingRepository;
    @NonNull
    private VehicleRepository vehicleRepository;

    @Override
    public void addVehicleToBranch(String branchName, String vehicleType,
            String vehicleId, double price) {

        Branch branch = branchService.getBranch(branchName);
        if (branch.getVehicleTypes().contains(vehicleType)) {
            Vehicle vehicle = new Vehicle(vehicleId, branchName, vehicleType, price);
            vehicleRepository.save(vehicle);
        } else {
            throw new InvalidVehicleTypeException();
        }
    }

    @Override
    public List<Vehicle> getAvailableByBranch(String branchId, int startTime, int endTime) {

        return getAvailable(
                bookingRepository.findByBranchIdAndWithinTime(branchId, startTime, endTime),
                vehicleRepository.findByBranchId(branchId));
    }

    @Override
    public List<Vehicle> getAvailableByBranchAndVehicleType(String branchId, String vehicleType, int startTime,
            int endTime) {

        return getAvailable(
                bookingRepository.findByBranchIdAndTypeAndWithinTime(branchId, vehicleType, startTime, endTime),
                vehicleRepository.findByBranchIdAndVehicleType(branchId, vehicleType));
    }

    private List<Vehicle> getAvailable(List<Booking> bookingList, List<Vehicle> vehicleList) {

        // get IDs of vehicles of given type, that are booked in the requested time slot
        Set<String> bookedVehicleIds = bookingList.stream()
                .map((booking) -> {
                    return booking.getVehicleId();
                }).collect(Collectors.toSet());

        // get IDs of vehicles sorted by price after filtering out the booked ones
        List<Vehicle> vehiclesFiltered = vehicleList.stream()
                // only pick vehicles that are not booked
                .filter((vehicle) -> {
                    return !bookedVehicleIds.contains(vehicle.getVehicleId());
                })
                .collect(Collectors.toList());

        return vehiclesFiltered;
    }
}
