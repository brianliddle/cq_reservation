package com.campsite.model;

import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    private int id;

    private int campsiteId;

    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation() {}

    public Reservation(int id, int campsiteId, LocalDate startdate, LocalDate enddate) {
        this.id = id;
        this.campsiteId = campsiteId;
        this.startDate = startdate;
        this.endDate = enddate;
    }

    public int getId() {
        return id;
    }

    public int getCampsiteId() {
        return campsiteId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

/* CONSIDER FOR FUTURE DEVELOPMENT */
//    private String reserveeId;
//    private int numberOfGuests;
}
