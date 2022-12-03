package com.jacobconner.artifacttracker.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.jacobconner.artifacttracker.dao.SiteDao;
import com.jacobconner.artifacttracker.database.ArchaeologyDB;
import com.jacobconner.artifacttracker.domain.Site;

import java.util.List;

public class SiteRepository {
    private SiteDao siteDao;
    private LiveData<List<Site>> sites;

    public SiteRepository(Application application) {
        ArchaeologyDB db = ArchaeologyDB.getDatabase(application);
        siteDao = db.siteDao();
        sites = siteDao.selectSites();
    }

    public LiveData<List<Site>> getSites() {
        return sites;
    }

    public void insert(Site site) {
        ArchaeologyDB.databaseWriteExecutor.execute(() -> {
            siteDao.insert(site);
        });
    }

}
