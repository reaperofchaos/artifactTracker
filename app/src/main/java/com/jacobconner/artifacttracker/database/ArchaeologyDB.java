package com.jacobconner.artifacttracker.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jacobconner.artifacttracker.dao.ArtifactDao;
import com.jacobconner.artifacttracker.dao.ArtifactTypeDao;
import com.jacobconner.artifacttracker.dao.LayerDao;
import com.jacobconner.artifacttracker.dao.SettingDao;
import com.jacobconner.artifacttracker.dao.SiteDao;
import com.jacobconner.artifacttracker.dao.UnitDao;
import com.jacobconner.artifacttracker.domain.Artifact;
import com.jacobconner.artifacttracker.domain.ArtifactType;
import com.jacobconner.artifacttracker.domain.Layer;
import com.jacobconner.artifacttracker.domain.Setting;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.domain.Unit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        ArtifactType.class,
        Setting.class,
        Artifact.class,
        Site.class,
        Unit.class,
        Layer.class},
        version = 2, exportSchema = false)
public abstract class ArchaeologyDB extends RoomDatabase {

    public abstract ArtifactDao artifactdao();
    public abstract ArtifactTypeDao artifactTypeDao();
    public abstract LayerDao layerDao();
    public abstract SettingDao settingDao();
    public abstract SiteDao siteDao();
    public abstract UnitDao unitDao();

    private static volatile ArchaeologyDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ArchaeologyDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ArchaeologyDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ArchaeologyDB.class, "archaeologyDB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}