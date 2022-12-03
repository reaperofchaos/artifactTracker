package com.jacobconner.artifacttracker.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "artifact_type")
public class ArtifactType {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    public String artifactType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtifactType() {
        return artifactType;
    }

    public void setArtifactType(String artifactType) {
        this.artifactType = artifactType;
    }
}
