package com.jacobconner.artifacttracker.viewcontroller;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jacobconner.artifacttracker.domain.ArtifactType;
import com.jacobconner.artifacttracker.repository.ArtifactTypeRepository;

import java.util.List;

public class ArtifactTypeViewModel  extends AndroidViewModel {
    private ArtifactTypeRepository artifactTypeRepository;

    private final LiveData<List<ArtifactType>> artifactTypes;

    public ArtifactTypeViewModel (Application application) {
        super(application);
        artifactTypeRepository = new ArtifactTypeRepository(application);
        artifactTypes = artifactTypeRepository.getArtifactTypes();
    }

    LiveData<List<ArtifactType>> getArtifactTypes() { return artifactTypes; }

    public void insert(ArtifactType artifactType) { artifactTypeRepository.insert(artifactType); }
}
