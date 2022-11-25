package com.jacobconner.artifacttracker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AddArtifactForm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artifact_form);
        initToolbar();
        initAddButtons();
        initSaveButton();
    }

    private void initSaveButton(){
        Button saveButton = findViewById(R.id.btnSaveArtifact);
        Spinner selectSite = findViewById(R.id.selectSiteName);
        Spinner selectUnit = findViewById(R.id.selectUnit);
        Spinner selectLayer = findViewById(R.id.selectLayer);
        Spinner selectArtifactType = findViewById(R.id.selectArtifactType);
        TextView artifactId = findViewById(R.id.txtArtifactId);
        TextView xCoord = findViewById(R.id.txtX);
        TextView yCoord = findViewById(R.id.txtY);
        TextView zCoord = findViewById(R.id.txtZ);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //create an artifact and add to database
            }
        });
    }

    private void initToolbar(){
        initArtifactButton();
        initViewArtifactButton();
        initGraphButton();
        initSettingsButton();
    }
    private void initAddButtons(){
        initAddSiteButton();
        initAddLayerButton();
        initAddUnitButton();
        initAddArtifactTypeButton();
    }
    private void initAddSiteButton(){
        ImageButton addSiteButton = findViewById(R.id.btnAddSite);
        addSiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactForm.this, AddSiteForm.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAddUnitButton(){
        ImageButton addUnitButton = findViewById(R.id.btnAddUnit);
        addUnitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactForm.this, AddUnitForm.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAddLayerButton(){
        ImageButton addLayerButton = findViewById(R.id.btnAddLayer);
        addLayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactForm.this, add_layer_form.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAddArtifactTypeButton(){
        ImageButton addArtifactButton = findViewById(R.id.btnAddArtifactType);
        addArtifactButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactForm.this, AddArtifactType.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initArtifactButton() {
        ImageButton artifactBtn = findViewById(R.id.btnAddArtifact);
        artifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactForm.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initViewArtifactButton() {
        ImageButton viewArtifactBtn = findViewById(R.id.btnViewArtifact);
        viewArtifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactForm.this, ViewArtifactsView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initGraphButton(){
        ImageButton btnGraph = findViewById(R.id.btnGraph);
        btnGraph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactForm.this, graph_view.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initSettingsButton(){
        ImageButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactForm.this, settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}