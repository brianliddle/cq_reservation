package com.campsite.dao.impl;

import com.campsite.dao.CampsiteDAO;
import com.campsite.model.Campsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** THIS CLASS USED EXCLUSIVELY FOR TEST CASES, all other cases should use a repository **/
public class CampsiteTestCache implements CampsiteDAO{

    /**
     * Leveraging a map to assist with retrieval of campsites.
     */
    Map<Integer, Campsite> campsites = new HashMap<Integer, Campsite>();

    /**
     * Returns a list of all campsites
     * @return
     */
    public List<Campsite> getCampsites() {
        return new ArrayList<Campsite>(campsites.values());
    }

    /**
     * Returns a campsite.
     * @param campsiteId
     * @return
     */
    public Campsite getCampsite(int campsiteId) {
        if (campsites.containsKey(campsiteId)) {
            return campsites.get(campsiteId);
        }   else {
            throw new RuntimeException("Campsite, "+campsiteId+", does not exist.");
        }
    }

    /**
     * Adds a campsite if it doesn't already exist.
     * @param camp
     */
    public void addCampsite(Campsite camp) {
        if (camp == null) {
            throw new RuntimeException("Can't add a null Campsite");
        }

        else if (camp == null ||
            camp.getId() <= 0 ||
            campsites.containsKey(camp.getId()))
        {
            throw new RuntimeException("Campsite, "+camp.getId()+", already exists.");

        }   else {
            campsites.put(camp.getId(), camp);
        }
    }

    /**
     * updates a campsite if it already exists.
     * @param camp
     */
    public void updateCampsite(Campsite camp) {
        if (camp == null) {
            throw new RuntimeException("Can't update null campsite.");
        }

        else if (camp.getId() > 0 &&
                 campsites.containsKey(camp.getId()))
        {
            campsites.put(camp.getId(), camp);

        }   else {
            throw new RuntimeException("Campsite, "+camp.getId()+", does not exist.");
        }
    }

    /**
     * Deletes an existing campsite.
     * @param campsiteId
     */
    public void deleteCampsite(int campsiteId) {
        if (campsites.containsKey(campsiteId)) {
            campsites.remove(campsiteId);
        }
    }

    /** Test Utilities **/

    /**
     * Adds campsites in bulk.
     * NOTE: Currently this is used exclusively for running unit tests.
     * @param camps
     */
    public void addCampsites(List<Campsite> camps) {
        for (Campsite camp : camps) {
            campsites.put(camp.getId(), camp);
        }
    }

    /**
     * Clears all campsites.
     * NOTE: Currently this is used exclusively for running unit tests.
     */
    public void clearCampsites() {
        campsites.clear();
    }

}
