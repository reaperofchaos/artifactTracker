package com.jacobconner.artifacttracker.viewcontroller;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.jacobconner.artifacttracker.domain.Artifact;
import com.jacobconner.artifacttracker.repository.ArtifactRepository;

import java.util.List;

public class ArtifactViewModel extends AndroidViewModel {
    private ArtifactRepository artifactRepository;

    private final LiveData<List<Artifact>> artifacts;

    public ArtifactViewModel(Application application) {
        super(application);
        artifactRepository = new ArtifactRepository(application);
        artifacts = artifactRepository.getArtifacts();
    }

    LiveData<List<Artifact>> getArtifacts() { return artifacts; }

    public void insert(Artifact artifact) { artifactRepository.insert(artifact); }
}
