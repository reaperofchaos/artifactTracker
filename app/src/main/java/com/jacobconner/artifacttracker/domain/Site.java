package com.jacobconner.artifacttracker.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "site")
public class Site {
    @PrimaryKey(autoGenerate = true)
    @NonNull
     int id;
     String siteName;
     float latitude;
     float longitude;
}
