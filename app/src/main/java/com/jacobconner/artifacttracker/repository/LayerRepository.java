package com.jacobconner.artifacttracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jacobconner.artifacttracker.dao.LayerDao;
import com.jacobconner.artifacttracker.dao.UnitDao;
import com.jacobconner.artifacttracker.database.ArchaeologyDB;
import com.jacobconner.artifacttracker.domain.Layer;
import com.jacobconner.artifacttracker.domain.Unit;

import java.util.List;

public class LayerRepository {
    private LayerDao layerDao;
    private LiveData<List<Layer>> layers;

    public LayerRepository(Application application) {
        ArchaeologyDB db = ArchaeologyDB.getDatabase(application);
        layerDao = db.layerDao();
        layers = layerDao.selectLayers();
    }

    public LiveData<List<Layer>> getLayers() {
        return layers;
    }

    public void insert(Layer layer) {
        ArchaeologyDB.databaseWriteExecutor.execute(() -> {
            layerDao.insert(layer);
        });
    }

}
