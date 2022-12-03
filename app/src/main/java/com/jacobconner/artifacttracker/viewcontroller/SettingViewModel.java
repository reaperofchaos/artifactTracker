package com.jacobconner.artifacttracker.viewcontroller;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jacobconner.artifacttracker.domain.Setting;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.repository.SettingRepository;
import com.jacobconner.artifacttracker.repository.SiteRepository;

import java.util.List;

public class SettingViewModel extends AndroidViewModel {
    private SettingRepository settingRepository;

    private final LiveData<Setting> setting;

    public SettingViewModel(Application application) {
        super(application);
        settingRepository = new SettingRepository(application);
        setting = settingRepository.getSetting();
    }

    LiveData<Setting> getSetting() { return setting; }

    public void insert(Setting setting) { SettingRepository.insert(setting); }
}
