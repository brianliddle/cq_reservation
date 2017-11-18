package com.campsite.model;

import java.util.Date;

public class Reservation {
    private int Id;

    /** TODO: Consider use of Campsite object to simplify initial business logic **/
    private int campsiteId;

    private Date startDate;
    private Date endDate;

    public Reservation() {}

    public Reservation(int id, int campsiteId, Date startdate, Date enddate) {
        this.Id = id;
        this.campsiteId = campsiteId;
        this.startDate = startdate;
        this.endDate = enddate;
    }

    public int getId() {
        return Id;
    }

    public int getCampsiteId() {
        return campsiteId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

/* CONSIDER FOR FUTURE DEVELOPMENT */
//    private String reserveeId;
//    private int numberOfGuests;
}
