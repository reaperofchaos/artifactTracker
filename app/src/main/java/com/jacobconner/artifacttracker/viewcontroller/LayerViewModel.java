package com.jacobconner.artifacttracker.viewcontroller;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jacobconner.artifacttracker.domain.Layer;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.repository.LayerRepository;
import com.jacobconner.artifacttracker.repository.SiteRepository;

import java.util.List;

public class LayerViewModel extends AndroidViewModel {
    private LayerRepository layerRepository;

    private final LiveData<List<Layer>> layers;

    public LayerViewModel(Application application) {
        super(application);
        layerRepository = new LayerRepository(application);
        layers = layerRepository.getLayers();
    }

    LiveData<List<Layer>> getLayers() { return layers; }

    public void insert(Layer layer) { layerRepository.insert(layer); }
}
