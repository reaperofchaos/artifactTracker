package com.jacobconner.artifacttracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jacobconner.artifacttracker.domain.Artifact;
import com.jacobconner.artifacttracker.domain.ArtifactType;

import java.util.List;

@Dao
public interface ArtifactDao {
    /**
     * Adds an ArtifactType
     * @param artifact
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Artifact artifact);

    /**
     * Deletes one contact record in  the contact table
     * @param id, artifact id in db
     */
    @Query("DELETE FROM artifact WHERE id=:id")
    void deleteArtifact(String id);

    /**
     * Selects all Artifact Type
     * @return a list of artifact_type
     */
    @Query("SELECT * FROM artifact")
    LiveData<List<Artifact>> selectArtifacts();

    /**
     * Selects all Artifact Type by site id
     * @return a list of artifacts
     */
    @Query("SELECT * FROM artifact WHERE siteId=:id")
    LiveData<List<Artifact>> selectArtifactsById(Integer id);


    /**
     * Selects a Artifact Type by name
     * @param id, artifact id in db
     * @return a artifact
     */
    @Query("SELECT * FROM artifact_type WHERE id=:id LIMIT 1")
    LiveData<ArtifactType> selectArtifactById(int id);
}
