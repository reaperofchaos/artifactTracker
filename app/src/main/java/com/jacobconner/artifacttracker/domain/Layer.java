package com.jacobconner.artifacttracker.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "layer")
public class Layer {
    @PrimaryKey(autoGenerate = true)
    @NonNull
     int id;
     int siteId;
     int unitId;
     String layerName;
}
