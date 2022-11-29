package com.jacobconner.artifacttracker.domain;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "setting")
public class Setting {
    @PrimaryKey(autoGenerate = true)
    @NonNull
     int id;
     String serverAddress;
     int port;
     String userName;
     String password;
     String dbType;
}
