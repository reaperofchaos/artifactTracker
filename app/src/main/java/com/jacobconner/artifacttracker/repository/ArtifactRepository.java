package com.jacobconner.artifacttracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jacobconner.artifacttracker.dao.ArtifactDao;
import com.jacobconner.artifacttracker.dao.LayerDao;
import com.jacobconner.artifacttracker.database.ArchaeologyDB;
import com.jacobconner.artifacttracker.domain.Artifact;
import com.jacobconner.artifacttracker.domain.Layer;

import java.util.List;

public class ArtifactRepository {
    private ArtifactDao artifactDao;
    private LiveData<List<Artifact>> artifacts;

    public ArtifactRepository(Application application) {
        ArchaeologyDB db = ArchaeologyDB.getDatabase(application);
        artifactDao = db.artifactdao();
        artifacts = artifactDao.selectArtifacts();
    }

    public LiveData<List<Artifact>> getArtifacts() {
        return artifacts;
    }

    public void insert(Artifact artifact) {
        ArchaeologyDB.databaseWriteExecutor.execute(() -> {
            artifactDao.insert(artifact);
        });
    }

}
