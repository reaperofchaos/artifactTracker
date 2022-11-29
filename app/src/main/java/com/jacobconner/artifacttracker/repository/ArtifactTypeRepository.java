package com.jacobconner.artifacttracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jacobconner.artifacttracker.dao.ArtifactTypeDao;
import com.jacobconner.artifacttracker.database.ArchaeologyDB;
import com.jacobconner.artifacttracker.domain.ArtifactType;

import java.util.List;

public class ArtifactTypeRepository {
    private ArtifactTypeDao artifactTypeDao;
    private LiveData<List<ArtifactType>> artifactTypes;

    public ArtifactTypeRepository(Application application) {
        ArchaeologyDB db = ArchaeologyDB.getDatabase(application);
        artifactTypeDao = db.artifactTypeDao();
        artifactTypes = artifactTypeDao.selectArtifactTypes();
    }

    public LiveData<List<ArtifactType>> getArtifactTypes() {
        return artifactTypes;
    }

    public void insert(ArtifactType artifactType) {
        ArchaeologyDB.databaseWriteExecutor.execute(() -> {
            artifactTypeDao.insert(artifactType);
        });
    }

}
