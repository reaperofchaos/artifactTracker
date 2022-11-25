package com.jacobconner.artifacttracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class add_layer_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_layer_form);
        initToolbar();
        initAddUnitButton();
        initAddSiteButton();
        initSaveButton();
    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.btnSaveLayer);
        Spinner selectSite = findViewById(R.id.selectSiteName);
        Spinner selectUnit = findViewById(R.id.selectUnit);
        TextView layerName = findViewById(R.id.txtLayerName);
        TextView layerId = findViewById(R.id.txtLayerId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               //create a layer and add to database
            }
        });
    }

    private void initAddUnitButton(){
        ImageButton addSiteButton = findViewById(R.id.btnAddUnit);
        addSiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(add_layer_form.this, AddSiteForm.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAddSiteButton(){
        ImageButton addSiteButton = findViewById(R.id.btnAddSite);
        addSiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(add_layer_form.this, AddSiteForm.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initToolbar(){
        initArtifactButton();
        initViewArtifactButton();
        initGraphButton();
        initSettingsButton();
    }

    private void initArtifactButton() {
        ImageButton artifactBtn = findViewById(R.id.btnAddArtifact);
        artifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(add_layer_form.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initViewArtifactButton() {
        ImageButton viewArtifactBtn = findViewById(R.id.btnViewArtifact);
        viewArtifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(add_layer_form.this, ViewArtifactsView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initGraphButton(){
        ImageButton btnGraph = findViewById(R.id.btnGraph);
        btnGraph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(add_layer_form.this, graph_view.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initSettingsButton(){
        ImageButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(add_layer_form.this, settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}