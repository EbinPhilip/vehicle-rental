package com.ebin.vehiclerental.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Booking {

    @Getter
    @NonNull
    private UUID bookingId;

    @Getter
    @NonNull
    private String vehicleId;

    @Getter
    @NonNull
    private String branchId;

    @Getter
    @NonNull
    private Integer startTime;

    @Getter
    @NonNull
    private Integer endTime;

    @Getter
    @NonNull
    private Double price;
}
