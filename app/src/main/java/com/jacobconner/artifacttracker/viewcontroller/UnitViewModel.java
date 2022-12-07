package com.jacobconner.artifacttracker.viewcontroller;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jacobconner.artifacttracker.domain.Layer;
import com.jacobconner.artifacttracker.domain.Unit;
import com.jacobconner.artifacttracker.repository.LayerRepository;
import com.jacobconner.artifacttracker.repository.UnitRepository;

import java.util.List;

public class UnitViewModel extends AndroidViewModel {
    private UnitRepository unitRepository;

    private final LiveData<List<Unit>> units;

    public UnitViewModel(Application application) {
        super(application);
        unitRepository = new UnitRepository(application);
        units = UnitRepository.getUnits();
    }

    public LiveData<List<Unit>> getUnits() { return units; }

    public void insert(Unit unit) { unitRepository.insert(unit); }
}
