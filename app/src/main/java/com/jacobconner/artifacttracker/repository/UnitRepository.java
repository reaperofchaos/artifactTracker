package com.jacobconner.artifacttracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jacobconner.artifacttracker.dao.SiteDao;
import com.jacobconner.artifacttracker.dao.UnitDao;
import com.jacobconner.artifacttracker.database.ArchaeologyDB;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.domain.Unit;

import java.util.List;

public class UnitRepository {
    private UnitDao unitDao;
    private static LiveData<List<Unit>> units;

    public UnitRepository(Application application) {
        ArchaeologyDB db = ArchaeologyDB.getDatabase(application);
        unitDao = db.unitDao();
        units = unitDao.selectUnits();
    }

    public static LiveData<List<Unit>> getUnits() {
        return units;
    }

    public void insert(Unit unit) {
        ArchaeologyDB.databaseWriteExecutor.execute(() -> {
            unitDao.insert(unit);
        });
    }

}
