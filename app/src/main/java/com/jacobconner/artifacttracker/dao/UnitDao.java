package com.jacobconner.artifacttracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jacobconner.artifacttracker.domain.ArtifactType;
import com.jacobconner.artifacttracker.domain.Unit;

import java.util.List;

@Dao
public interface UnitDao {
    /**
     * Adds a Unit
     * @param unit
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Unit unit);

    /**
     * Deletes one unit record in  the unit table
     * @param id, unit id  in db
     */
    @Query("DELETE FROM unit WHERE id=:id")
    void deleteUnit(int id);

    /**
     * Selects all units
     * @return a list of unit
     */
    @Query("SELECT * FROM unit")
    LiveData<List<Unit>> selectUnits();


    /**
     * Selects all units for a site
     * @return a list of unit
     */
    @Query("SELECT * FROM unit where siteId=:id")
    LiveData<List<Unit>> selectUnitInSite(int id);

    /**
     * Selects a unit Type by id
     * @param id, unit id  in db
     * @return a unit
     */
    @Query("SELECT * FROM unit WHERE id=:id LIMIT 1")
    LiveData<Unit> selectUnitBydId(int id);
}
