package com.example.restaurant_system.restaurant.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OperatingHours {

    private TimeRange monday;
    private TimeRange tuesday;
    private TimeRange wednesday;
    private TimeRange thursday;
    private TimeRange friday;
    private TimeRange saturday;
    private TimeRange sunday;

    public OperatingHours() {
    }

    public OperatingHours(
            TimeRange monday,
            TimeRange tuesday,
            TimeRange wednesday,
            TimeRange thursday,
            TimeRange friday,
            TimeRange saturday,
            TimeRange sunday
    ) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

}
