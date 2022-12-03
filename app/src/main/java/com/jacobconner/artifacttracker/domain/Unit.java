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
    public int id;
    public int siteId;
    public String unitName;
    public float unitWidth;
    public float unitHeight;
    public String heightUnits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public float getUnitWidth() {
        return unitWidth;
    }

    public void setUnitWidth(float unitWidth) {
        this.unitWidth = unitWidth;
    }

    public float getUnitHeight() {
        return unitHeight;
    }

    public void setUnitHeight(float unitHeight) {
        this.unitHeight = unitHeight;
    }

    public String getHeightUnits() {
        return heightUnits;
    }

    public void setHeightUnits(String heightUnits) {
        this.heightUnits = heightUnits;
    }

    public String getWidthUnits() {
        return widthUnits;
    }

    public void setWidthUnits(String widthUnits) {
        this.widthUnits = widthUnits;
    }

    public String widthUnits;
}
