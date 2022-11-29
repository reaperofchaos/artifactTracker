package com.jacobconner.artifacttracker.domain;

import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "unit" )
public class Unit {
    @PrimaryKey(autoGenerate = true)
    @NonNull
     int id;
     int siteId;
     String unitName;
     float unitWidth;
     float unitHeight;
     String heightUnits;
     String widthUnits;
}
