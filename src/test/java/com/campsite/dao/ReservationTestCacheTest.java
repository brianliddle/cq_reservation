package com.campsite.dao;

import com.campsite.ReservationBaseTest;
import com.campsite.business.ReservationHashKey;
import com.campsite.model.Reservation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static com.campsite.business.ReservationHashKey.NEXT;
import static com.campsite.business.ReservationHashKey.PRIOR;
import static com.campsite.business.ReservationHashKey.CONFLICT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    public void getNextPriorReservation() {
        LocalDate startDate = LocalDate.of(2016,6,5);
        LocalDate endDate = LocalDate.of(2016,6,6);
        int campId = 10;

        Map<ReservationHashKey, Reservation> nextPriorMap = rezDao.getNextPriorReservation(startDate, endDate, campId);
        Assert.assertTrue("CampsiteId should be, 10", campId == nextPriorMap.get(PRIOR).getCampsiteId());
        Assert.assertTrue("Prior Reservation should end 2016/06/04.", nextPriorMap.get(PRIOR).getEndDate().getDayOfMonth() == 4 );
        Assert.assertTrue("Next Reservation should end 2016/06/07.", nextPriorMap.get(NEXT).getStartDate().getDayOfMonth() == 7 );
    }

    @Test
    public void getNextPriorRes_noPriorReservationsExist() {
        LocalDate startDate = LocalDate.of(2016, 5, 5);
        LocalDate endDate = LocalDate.of(2016,5,8);
        int campId = 10;

        Map<ReservationHashKey, Reservation> nextPriorMap = rezDao.getNextPriorReservation(startDate, endDate, campId);
        Assert.assertTrue("PRIOR reservation should be null.", nextPriorMap.get(PRIOR) == null);
        Assert.assertTrue("Next Reservation should end 2016/06/07.", nextPriorMap.get(NEXT).getStartDate().getDayOfMonth() == 25 );
        Assert.assertTrue("CONFLICT reservation should not exist.", !nextPriorMap.containsKey(CONFLICT));
    }

    @Test
    public void getNextPriorRes_targetInMiddleOfReservation() {
        LocalDate startDate = LocalDate.of(2016, 6, 2);
        LocalDate endDate = LocalDate.of(2016,6,4);
        int campId = 10;

        Map<ReservationHashKey, Reservation> nextPriorMap = rezDao.getNextPriorReservation(startDate, endDate, campId);
        Assert.assertTrue("No prior reservation should exist.", !nextPriorMap.containsKey(PRIOR));
        Assert.assertTrue("No next reservation should exist.", !nextPriorMap.containsKey(NEXT));
        Assert.assertTrue("Conflict should be defined.", nextPriorMap.containsKey(CONFLICT));
    }
}
