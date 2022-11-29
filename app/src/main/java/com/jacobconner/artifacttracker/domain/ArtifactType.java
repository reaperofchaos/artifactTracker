package com.jacobconner.artifacttracker.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "artifact_type")
public class ArtifactType {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;
    String artifactType;
}
