package com.jacobconner.artifacttracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.jacobconner.artifacttracker.domain.ArtifactType;
import com.jacobconner.artifacttracker.domain.Setting;
import java.util.List;

@Dao
public interface SettingDao {
    /**
     * Adds a setting
     * @param setting
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Setting setting);

    /**
     * Deletes one contact record in  the contact table
     * @param id, artifact type in db
     */
    @Query("DELETE FROM setting WHERE id=:id")
    void deleteSetting(int id);

    /**
     * Selects setting
     * @return a single setting
     */
    @Query("SELECT * FROM setting LIMIT 1")
    LiveData<Setting> selectSetting();

}
