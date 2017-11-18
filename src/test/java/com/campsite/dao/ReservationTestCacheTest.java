package com.campsite.dao;

import com.campsite.ReservationBaseTest;
import com.campsite.model.Reservation;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;


/** TODO: leverage dependency injection so that we test against the interface, not the concrete class **/
public class ReservationTestCacheTest extends ReservationBaseTest{

//    @Test
//    public void getReservations() {
//        List<Reservation> rez = getReservations(LocalDate startDate, LocalDate endDate, int campsiteId)
//    }

    @Test
    public void getPriorReservation() {
        Assert.assertEquals("TODO", true, false);
    }

    @Test
    public void getNextReservation() {
        Assert.assertEquals("TODO", true, false);
    }
}
