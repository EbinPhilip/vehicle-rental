package com.ebin.vehiclerental.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Vehicle {

    @Getter
    @NonNull
    private String id;

    @Getter
    @NonNull
    private String branchName;

    @Getter
    @NonNull
    private String type;

    @Getter
    @NonNull
    private Double price;
}
