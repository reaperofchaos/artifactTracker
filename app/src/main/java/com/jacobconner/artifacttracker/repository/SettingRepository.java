package com.jacobconner.artifacttracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jacobconner.artifacttracker.dao.ArtifactDao;
import com.jacobconner.artifacttracker.dao.SettingDao;
import com.jacobconner.artifacttracker.database.ArchaeologyDB;
import com.jacobconner.artifacttracker.domain.Artifact;
import com.jacobconner.artifacttracker.domain.Setting;

import java.util.List;

public class SettingRepository {
    private static SettingDao settingDao;
    private LiveData<Setting> setting;

    public SettingRepository(Application application) {
        ArchaeologyDB db = ArchaeologyDB.getDatabase(application);
        settingDao = db.settingDao();
        setting = settingDao.selectSetting();
    }

    public LiveData<Setting> getSetting() {
        return setting;
    }

    public static void insert(Setting setting) {
        ArchaeologyDB.databaseWriteExecutor.execute(() -> {
            settingDao.insert(setting);
        });
    }

}
