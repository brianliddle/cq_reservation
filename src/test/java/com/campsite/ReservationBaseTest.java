package com.campsite;

import com.campsite.model.Campsite;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;
import com.campsite.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.File;
import java.util.*;

public class ReservationBaseTest {

    public static String testFile = "src/test/resources/test-case.json";

    /** Resources **/
    public ReservationService rezSvc = new ReservationService();

    /** Test Objects **/
    public static List<Reservation> testRez = new ArrayList<Reservation>();
    public static List<Campsite> testCamps = new ArrayList<Campsite>();
    public static List<Gaprule> gapRules = new ArrayList<Gaprule>();
    public static Date startDate;
    public static Date endDate;


    @BeforeClass
    public static void dataSetup() {
        ObjectMapper mapper = new ObjectMapper();
        try {

            HashMap<String, Object> testMap = mapper.readValue(new File(testFile), HashMap.class);

            HashMap<String, Object> searchMap = (HashMap<String, Object>)testMap.get("search");
            startDate = mapper.convertValue(searchMap.get("startDate"), Date.class);
            endDate = mapper.convertValue(searchMap.get("endDate"), Date.class);
            System.out.println("Start Date:\t" + startDate);
            System.out.println("End Date:\t" + endDate);

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
                Reservation r = mapper.convertValue(rIter.next(), Reservation.class);
                testRez.add(r);
            }

            ArrayList<HashMap<String, Object>> campMap =
                    (ArrayList<HashMap<String, Object>>) testMap.get("campsites");

            Iterator cIter = campMap.iterator();
            while (cIter.hasNext()) {
                Campsite c = mapper.convertValue(cIter.next(), Campsite.class);
                testCamps.add(c);
            }

        }catch (Exception e) {
            Assert.assertEquals("Failed to load resources!!", true,false);
            e.printStackTrace();
        }
    }
}
