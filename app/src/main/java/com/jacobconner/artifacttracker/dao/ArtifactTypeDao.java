package com.jacobconner.artifacttracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jacobconner.artifacttracker.domain.ArtifactType;

import java.util.List;

@Dao
public interface ArtifactTypeDao {
    /**
     * Adds an ArtifactType
     * @param artifactType
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ArtifactType artifactType);

    /**
     * Deletes one contact record in  the contact table
     * @param type, artifact type in db
     */
    @Query("DELETE FROM artifact_type WHERE artifactType=:type")
    void deleteType(String type);

    /**
     * Selects all Artifact Type
     * @return a list of artifact_type
     */
    @Query("SELECT * FROM artifact_type")
    LiveData<List<ArtifactType>> selectArtifactTypes();

    /**
     * Selects a Artifact Type by name
     * @param type, artifact type in db
     * @return a artifact type
     */
    @Query("SELECT * FROM artifact_type WHERE artifactType=:type LIMIT 1")
    LiveData<ArtifactType> selectArtifactByType(String type);
}
