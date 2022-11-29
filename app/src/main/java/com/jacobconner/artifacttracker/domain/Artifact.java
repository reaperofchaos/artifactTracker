package com.jacobconner.artifacttracker.domain;

import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.jacobconner.artifacttracker.R;

@Entity(tableName = "artifact")
public class Artifact {
    @PrimaryKey(autoGenerate = true)
    @NonNull
     int id;
     int siteId;
     int unitId;
     int layerId;
     String artifactType;
     float xCoord;
     float yCoord;
     float zCoord;
}
