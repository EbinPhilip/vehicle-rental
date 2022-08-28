package com.ebin.vehiclerental.commands;

import java.util.List;

import com.ebin.vehiclerental.services.BookingService;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class BookCommand implements Command {

    @NonNull
    private final BookingService bookingService;

    @Override
    public void run(List<String> tokens) {

        try {
            String branchId = tokens.get(1).trim();
            String vehicleType = tokens.get(2).trim();
            int startTime = Integer.valueOf(tokens.get(3).trim());
            int endTime = Integer.valueOf(tokens.get(4).trim());

            double price = bookingService.bookVehicle(branchId, vehicleType, startTime, endTime);
            System.out.println(price);
        } catch (RuntimeException e) {
            System.out.println("-1");
        }
    }
}
