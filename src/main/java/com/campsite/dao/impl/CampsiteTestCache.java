package com.campsite.dao.impl;

import com.campsite.dao.CampsiteDAO;
import com.campsite.model.Campsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** THIS CLASS USED EXCLUSIVELY FOR TEST CASES, all other cases should use a repository **/
public class CampsiteTestCache implements CampsiteDAO{

    List<Campsite> campsites = new ArrayList<Campsite>();

    public List<Campsite> getCampsites() {
        return campsites;
    }

    /** Test Utilities **/
    public void addCampsites(List<Campsite> camps) {
        campsites.addAll(camps);
    }

    public void clearCampsites() {
        campsites.clear();
    }


//    public Campsite getCampsite(int campsiteId) {
//        if (campsites.containsKey(campsiteId)) {
//            return campsites.get(campsiteId);
//        }   else {
//            throw new RuntimeException("Campsite, "+campsiteId+", does not exist.");
//        }
//    }
//
//    public void addCampsite(Campsite camp) {
//        if (camp == null) {
//            throw new RuntimeException("Can't add a null Campsite");
//        }
//
//        else if (camp == null ||
//            camp.getId() <= 0 ||
//            campsites.containsKey(camp.getId()))
//        {
//            throw new RuntimeException("Campsite, "+camp.getId()+", already exists.");
//
//        }   else {
//            campsites.put(camp.getId(), camp);
//        }
//    }
//
//    public void updateCampsite(Campsite camp) {
//        if (camp == null) {
//            throw new RuntimeException("Can't update null campsite.");
//        }
//
//        else if (camp.getId() > 0 &&
//                 campsites.containsKey(camp.getId()))
//        {
//            campsites.put(camp.getId(), camp);
//
//        }   else {
//            throw new RuntimeException("Campsite, "+camp.getId()+", does not exist.");
//        }
//    }
//
//    public void deleteCampsite(int campsiteId) {
//        if (campsites.containsKey(campsiteId)) {
//            campsites.remove(campsiteId);
//        }
//    }
}