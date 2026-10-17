package com.example.restaurant_system.restaurant.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class TimeRange {

    private LocalTime openTime;
    private LocalTime closeTime;

    public TimeRange() {
    }

    public TimeRange(LocalTime openTime, LocalTime closeTime) {
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

}
