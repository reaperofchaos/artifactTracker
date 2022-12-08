package com.jacobconner.artifacttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jacobconner.artifacttracker.domain.Artifact;
import com.jacobconner.artifacttracker.viewcontroller.ArtifactViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewArtifactsView extends AppCompatActivity {
    private ArtifactViewModel artifactViewModel;
    ArtifactAdapter adapter;
    List<Artifact> artifactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_artifacts_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        artifactViewModel = new ViewModelProvider(this).get(ArtifactViewModel.class);
        adapter = new ArtifactAdapter((ArrayList<Artifact>) artifactList, ViewArtifactsView.this);

        artifactViewModel.getArtifacts().observe(this, artifacts -> {
            artifactList = artifacts;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        initArtifactButton();
        initViewArtifactButton();
        initGraphButton();
        initSettingsButton();
    }

    private void initArtifactButton() {
        ImageButton artifactBtn = findViewById(R.id.btnAddArtifact);
        artifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ViewArtifactsView.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initViewArtifactButton() {
        ImageButton viewArtifactBtn = findViewById(R.id.btnViewArtifact);
        viewArtifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ViewArtifactsView.this, ViewArtifactsView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initGraphButton(){
        ImageButton btnGraph = findViewById(R.id.btnGraph);
        btnGraph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ViewArtifactsView.this, graph_view.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initSettingsButton(){
        ImageButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ViewArtifactsView.this, settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}