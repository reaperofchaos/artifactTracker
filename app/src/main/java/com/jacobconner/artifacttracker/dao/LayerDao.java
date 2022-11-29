package com.jacobconner.artifacttracker.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jacobconner.artifacttracker.domain.ArtifactType;
import com.jacobconner.artifacttracker.domain.Layer;

import java.util.List;

@Dao
public interface LayerDao {
    /**
     * Adds an ArtifactType
     * @param layer
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Layer layer);

    /**
     * Deletes one contact record in  the contact table
     * @param id, artifact type in db
     */
    @Query("DELETE FROM layer WHERE id=:id")
    void deleteLayer(int id);

    /**
     * Selects all layers
     * @return a list of layers
     */
    @Query("SELECT * FROM layer")
    LiveData<List<Layer>> selectLayers();

    /**
     * Selects all layers by site id
     * @return a list of layers
     */
    @Query("SELECT * FROM layer WHERE siteId=:id")
    LiveData<List<Layer>> selectLayers(int id);

    /**
     * Selects a layer by id
     * @param id, layer in db
     * @return a layer type
     */
    @Query("SELECT * FROM layer WHERE id=:id LIMIT 1")
    LiveData<Layer> selectLayerById(int id);
}
