package com.ebin.vehiclerental.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Vehicle {

    @Getter
    @NonNull
    private String vehicleId;

    @Getter
    @NonNull
    private String branchName;

    @Getter
    @NonNull
    private String vehicleType;

    @Getter
    @NonNull
    private Double price;
}
