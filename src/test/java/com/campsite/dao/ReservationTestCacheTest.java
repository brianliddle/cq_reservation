package com.campsite.dao;

import com.campsite.ReservationBaseTest;
import com.campsite.model.Reservation;
import org.junit.Assert;
import org.junit.BeforeClass;
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
        r.add(new Reservation(rezId++,10,LocalDate.of(2016,5,25),LocalDate.of(2016,5,30)));
        r.add(new Reservation(rezId++,10,LocalDate.of(2016,6,1),LocalDate.of(2016,6,4)));
        r.add(new Reservation(rezId++,10,LocalDate.of(2016,6,7),LocalDate.of(2016,6,10)));
        r.add(new Reservation(rezId++,10,LocalDate.of(2016,6,12),LocalDate.of(2016,6,14)));
        r.add(new Reservation(rezId++,10,LocalDate.of(2016,6,20),LocalDate.of(2016,6,25)));
        r.add(new Reservation(rezId++,10,LocalDate.of(2016,6,26),LocalDate.of(2016,6,28)));
        r.add(new Reservation(rezId++,10,LocalDate.of(2016,7,1),LocalDate.of(2016,7,4)));
        rezDao.addReservations(r);
    }

    @Test
    public void getPriorReservation() {
        LocalDate startDate = LocalDate.of(2016, 6, 5);
        LocalDate endDate = LocalDate.of(2016,6,8);
        int campId = 10;

        try {
            Reservation priorRes = rezDao.getPriorReservation(startDate, endDate, campId);
            Assert.assertTrue("Prior reservation should not be null.", priorRes != null);
            Assert.assertTrue("CampsiteId should be, 10", campId == priorRes.getCampsiteId());
            Assert.assertTrue("Prior Reservation should end 2016/06/04.", priorRes.getEndDate().getDayOfMonth() == 4 );

        } catch (Exception e) { /** no-op **/ }

    }

    @Test
    public void getPriorRes_noPriorReservationsExist() {
        LocalDate startDate = LocalDate.of(2016, 5, 5);
        LocalDate endDate = LocalDate.of(2016,5,8);
        int campId = 10;
        try {
            Reservation priorRes = rezDao.getPriorReservation(startDate, endDate, campId);
            Assert.assertTrue("Prior reservation should be null.", priorRes == null);
        } catch (Exception e) { /** no-op **/ }
    }

    @Test
    public void getPriorRes_reservationSameAsTarget() {
        LocalDate startDate = LocalDate.of(2016, 6, 4);
        LocalDate endDate = LocalDate.of(2016,6,8);
        int campId = 10;
        try{
            Reservation priorRes = rezDao.getPriorReservation(startDate, endDate, campId);
            Assert.assertTrue("Prior reservation should not be null.", priorRes != null);
            Assert.assertTrue("Prior Reservation should end 2016/06/04.", priorRes.getEndDate().getDayOfMonth() == 4 );
        } catch (Exception e) { /** no-op **/ }
    }

    @Test
    public void getPriorRes_targetInMiddleOfReservation() {
        LocalDate startDate = LocalDate.of(2016, 6, 2);
        LocalDate endDate = LocalDate.of(2016,6,4);
        int campId = 10;
        try {
            Reservation priorRes = rezDao.getPriorReservation(startDate, endDate, campId);
            Assert.assertTrue("Prior reservation should not be null.", priorRes != null);
            Assert.assertTrue("Prior Reservation should end 2016/06/04.", priorRes.getEndDate().getDayOfMonth() == 4 );
        } catch (Exception e) { /** no-op **/ }
    }

    @Test
    public void getNextReservation() {
        LocalDate startDate = LocalDate.of(2016, 6, 2);
        LocalDate endDate = LocalDate.of(2016, 6, 5);
        int campId = 10;
        try {
            Reservation nextRes = rezDao.getNextReservation(startDate, endDate, campId);
            Assert.assertTrue("Next reservation should not be null.", nextRes != null);
            Assert.assertTrue("CampsiteId should be, 10", campId == nextRes.getCampsiteId());
            Assert.assertTrue("Next Reservation should begin 2016/6/7.", nextRes.getStartDate().getDayOfMonth() == 7);
        } catch (Exception e) { /** no-op **/ }
    }

    @Test
    public void getNextRes_noNextResExists() {
        LocalDate startDate = LocalDate.of(2016, 7, 3);
        LocalDate endDate = LocalDate.of(2016, 7, 5);
        int campId = 10;
        try{
            Reservation nextRes = rezDao.getNextReservation(startDate,endDate, campId);
            Assert.assertTrue("Next reservation should be null.", nextRes == null);
        } catch (Exception e) { /** no-op **/ }
    }

    @Test
    public void getNextRes_reservationSameAsTarget() {
        LocalDate startDate = LocalDate.of(2016, 6, 5);
        LocalDate endDate = LocalDate.of(2016, 6, 7);
        int campId = 10;
        try{
            Reservation nextRes = rezDao.getNextReservation(startDate, endDate, campId);
            Assert.assertTrue("Next reservation should not be null.", nextRes != null);
            Assert.assertTrue("Next Reservation should begin 2016/6/7.", nextRes.getStartDate().getDayOfMonth() == 7);
        } catch (Exception e) { /** no-op **/ }
    }

    @Test
    public void getNextRes_targetInMiddleOfReservation() {
        LocalDate startDate = LocalDate.of(2016, 6, 5);
        LocalDate endDate = LocalDate.of(2016, 6, 8);
        int campId = 10;
        try {
            Reservation nextRes = rezDao.getNextReservation(startDate, endDate, campId);
            Assert.assertTrue("Next reservation should not be null.", nextRes != null);
            Assert.assertTrue("Next Reservation should end 2016/6/10.", nextRes.getEndDate().getDayOfMonth() == 10);
        } catch (Exception e) { /** no-op **/ }
    }
}
