package com.campsite.dao;

import com.campsite.model.Campsite;

public interface CampsiteDAO {
    Campsite getCampsite(int campsiteId);

    void addCampsite(Campsite camp);

    void updateCampsite(Campsite camp);

    void deleteCampsite(int campsiteId);
}
