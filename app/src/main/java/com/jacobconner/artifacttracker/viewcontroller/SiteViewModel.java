package com.jacobconner.artifacttracker.viewcontroller;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.repository.SiteRepository;
import java.util.List;

public class SiteViewModel extends AndroidViewModel {
    private SiteRepository siteRepository;

    private final LiveData<List<Site>> sites;

    public SiteViewModel(Application application) {
        super(application);
        siteRepository = new SiteRepository(application);
        sites = siteRepository.getSites();
    }

    public LiveData<List<Site>> getSites() { return sites; }

    public void insert(Site site) { siteRepository.insert(site); }
}
