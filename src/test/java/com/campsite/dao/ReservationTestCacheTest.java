package com.campsite.dao;

import com.campsite.ReservationBaseTest;
import com.campsite.model.Reservation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/** TODO: leverage dependency injection so that we test against the interface, not the concrete class **/
public class ReservationTestCacheTest extends ReservationBaseTest{

    @BeforeClass
    public static void addReservationData() {
        List<Reservation> r = new ArrayList<Reservation>();
        int rezId = 100;
        r.add(new Reservation(rezId++,9,LocalDate.of(2016,5,25),LocalDate.of(2016,5,30)));
        r.add(new Reservation(rezId++,9,LocalDate.of(2016,6,1),LocalDate.of(2016,6,4)));
        r.add(new Reservation(rezId++,9,LocalDate.of(2016,6,7),LocalDate.of(2016,6,10)));
        r.add(new Reservation(rezId++,9,LocalDate.of(2016,6,12),LocalDate.of(2016,6,14)));
        r.add(new Reservation(rezId++,9,LocalDate.of(2016,6,20),LocalDate.of(2016,6,25)));
        r.add(new Reservation(rezId++,9,LocalDate.of(2016,6,26),LocalDate.of(2016,6,28)));
        r.add(new Reservation(rezId++,9,LocalDate.of(2016,7,1),LocalDate.of(2016,7,4)));
        rezDao.addReservations(r);
    }

    @Ignore
    @Test
    public void getReservations() {
        Assert.assertEquals("TODO", true, false);
    }

    @Test
    public void getPriorReservation() {
        LocalDate targetDate = LocalDate.of(2016, 6, 5);
        int campId = 9;
        Reservation priorRes = rezDao.getPriorReservation(targetDate, campId);
        Assert.assertTrue("Prior reservation should not be null.", priorRes != null);
        Assert.assertTrue("CampsiteId should be, 9", campId == priorRes.getCampsiteId());
        Assert.assertTrue("Prior Reservation should end 2016/06/04.", priorRes.getEndDate().getDayOfMonth() == 4 );
    }

    @Test
    public void getPriorRes_noPriorReservationsExist() {
        LocalDate targetDate = LocalDate.of(2016, 5, 5);
        int campId = 9;
        Reservation priorRes = rezDao.getPriorReservation(targetDate, campId);
        Assert.assertTrue("Prior reservation should be null.", priorRes == null);
    }

    @Test
    public void getPriorRes_reservationSameAsTarget() {
        LocalDate targetDate = LocalDate.of(2016, 6, 4);
        int campId = 9;
        Reservation priorRes = rezDao.getPriorReservation(targetDate, campId);
        Assert.assertTrue("Prior reservation should not be null.", priorRes != null);
        Assert.assertTrue("Prior Reservation should end 2016/06/04.", priorRes.getEndDate().getDayOfMonth() == 4 );
    }

    @Test
    public void getPriorRes_targetInMiddleOfReservation() {
        LocalDate targetDate = LocalDate.of(2016, 6, 2);
        int campId = 9;
        Reservation priorRes = rezDao.getPriorReservation(targetDate, campId);
        Assert.assertTrue("Prior reservation should not be null.", priorRes != null);
        Assert.assertTrue("Prior Reservation should end 2016/06/04.", priorRes.getEndDate().getDayOfMonth() == 4 );
    }

    @Test
    public void getNextReservation() {
        LocalDate targetDate = LocalDate.of(2016, 6, 5);
        int campId = 9;
        Reservation nextRes = rezDao.getNextReservation(targetDate, campId);
        Assert.assertTrue("Next reservation should not be null.", nextRes != null);
        Assert.assertTrue("CampsiteId should be, 9", campId == nextRes.getCampsiteId());
        Assert.assertTrue("Next Reservation should begin 2016/6/7.", nextRes.getStartDate().getDayOfMonth() == 7);
    }

    @Test
    public void getNextRes_noNextResExists() {
        LocalDate targetDate = LocalDate.of(2016, 7, 5);
        int campId = 9;
        Reservation nextRes = rezDao.getNextReservation(targetDate, campId);
        Assert.assertTrue("Next reservation should be null.", nextRes == null);
    }

    @Test
    public void getNextRes_reservationSameAsTarget() {
        LocalDate targetDate = LocalDate.of(2016, 6, 7);
        int campId = 9;
        Reservation nextRes = rezDao.getNextReservation(targetDate, campId);
        Assert.assertTrue("Next reservation should not be null.", nextRes != null);
        Assert.assertTrue("Next Reservation should begin 2016/6/7.", nextRes.getStartDate().getDayOfMonth() == 7);
    }

    @Test
    public void getNextRes_targetInMiddleOfReservation() {
        LocalDate targetDate = LocalDate.of(2016, 6, 8);
        int campId = 9;
        Reservation nextRes = rezDao.getNextReservation(targetDate, campId);
        Assert.assertTrue("Next reservation should not be null.", nextRes != null);
        Assert.assertTrue("Next Reservation should end 2016/6/10.", nextRes.getEndDate().getDayOfMonth() == 10);
    }
}
