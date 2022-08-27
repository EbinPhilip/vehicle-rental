package com.ebin.vehiclerental.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Branch {
  
    @NonNull
    @Getter
    private String name;

    @NonNull
    @Getter
    private List<String> vehicleTypes; 
}
