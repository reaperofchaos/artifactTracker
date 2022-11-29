package com.jacobconner.artifacttracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jacobconner.artifacttracker.domain.ArtifactType;
import com.jacobconner.artifacttracker.domain.Site;

import java.util.List;

@Dao
public interface SiteDao {
    /**
     * Adds a Site
     * @param site
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Site site);

    /**
     * Deletes one site record in  the site table
     * @param id site id in db
     */
    @Query("DELETE FROM site WHERE id=:id")
    void deleteById(int id);

    /**
     * Selects all sites
     * @return a list of sites
     */
    @Query("SELECT * FROM site")
    LiveData<List<Site>> selectSites();

    /**
     * Selects a site  by id
     * @param id, site id  in db
     * @return a site
     */
    @Query("SELECT * FROM site WHERE id=:id LIMIT 1")
    LiveData<Site> selectSiteById(int id);
}
