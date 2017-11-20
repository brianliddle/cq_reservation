package com.campsite;

import com.campsite.dao.impl.CampsiteTestCache;
import com.campsite.dao.impl.ReservationTestCache;
import com.campsite.model.Campsite;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;
import com.campsite.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Parent of integration tests, that utilize the same test-case.json test data.
 *
 * TODO: leverage depenency injection so that we're working against interfaces
 *       and not concrete implementations.
 */
public class ReservationBaseTest {

    public static String testFile = "src/test/resources/test-case.json";

    /** Resources **/
    public static ReservationService rezSvc = new ReservationService();
    public static ReservationTestCache rezDao = new ReservationTestCache();
    public static CampsiteTestCache campDao = new CampsiteTestCache();

    /** Test Objects **/
    public static List<Reservation> testRez = new ArrayList<Reservation>();
    public static List<Campsite> testCamps = new ArrayList<Campsite>();
    public static List<Gaprule> gapRules = new ArrayList<Gaprule>();
    public static LocalDate startDate;
    public static LocalDate endDate;


    @BeforeClass
    public static void dataSetup() {
        ObjectMapper mapper = new ObjectMapper();
        try {

            HashMap<String, Object> testMap = mapper.readValue(new File(testFile), HashMap.class);

            HashMap<String, Object> searchMap = (HashMap<String, Object>)testMap.get("search");

            startDate = toLocalDate(mapper.convertValue(searchMap.get("startDate"), Date.class));
            endDate = toLocalDate(mapper.convertValue(searchMap.get("endDate"), Date.class));

            ArrayList<HashMap<String, Object>> gapMap =
                    (ArrayList<HashMap<String, Object>>) testMap.get("gapRules");

            Iterator gIter = gapMap.iterator();
            while (gIter.hasNext()) {
                Gaprule g = mapper.convertValue(gIter.next(), Gaprule.class);
                gapRules.add(g);
            }

            ArrayList<HashMap<String, Object>> rezMap =
                    (ArrayList<HashMap<String, Object>>) testMap.get("reservations");

            Iterator rIter = rezMap.iterator();
            while (rIter.hasNext()) {
                //Due to issues with serializing LocalDate, the following doesn't work...
                //Reservation r = mapper.convertValue(rIter.next(), Reservation.class);
                HashMap<String, Object> rMap = (HashMap<String, Object>) rIter.next();

                Reservation r = new Reservation(
                        testRez.size(),
                        new Integer(rMap.get("campsiteId").toString()),
                        LocalDate.parse(rMap.get("startDate").toString()),
                        LocalDate.parse(rMap.get("endDate").toString()));
                testRez.add(r);
            }
            rezDao.clearReservations();
            rezDao.addReservations(testRez);
            rezSvc.setResDao(rezDao);

            ArrayList<HashMap<String, Object>> campMap =
                    (ArrayList<HashMap<String, Object>>) testMap.get("campsites");

            Iterator cIter = campMap.iterator();
            while (cIter.hasNext()) {
                Campsite c = mapper.convertValue(cIter.next(), Campsite.class);
                testCamps.add(c);
            }
            campDao.clearCampsites();
            campDao.addCampsites(testCamps);
            rezSvc.setCampDao(campDao);

        }catch (Exception e) {
            Assert.assertEquals("Failed to load resources!!", true, false);
            e.printStackTrace();
        }
    }

    // The following is a hack to convert Date to LocalDate since running into issues serializing
    // LocalDate with ObjectMapper
    private static LocalDate toLocalDate(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
    }
}
