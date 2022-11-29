package com.jacobconner.artifacttracker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.jacobconner.artifacttracker.databinding.ActivityAddUnitFormBinding;

import org.w3c.dom.Text;

public class AddUnitForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit_form);
        initToolbar();
        initAddSiteButton();
    }

    private void initAddSiteButton(){
        ImageButton addSiteButton = findViewById(R.id.btnAddSite);
        TextView unitName = findViewById(R.id.txtUnitName);
        TextView unitWidth = findViewById(R.id.txtUnitWidth);
        TextView unitHeight = findViewById(R.id.txtUnitHeight);
        Spinner heightUnits = findViewById(R.id.selectHeightUnits);
        Spinner widthUnits = findViewById(R.id.selectWidthUnits);

        addSiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //build a unit and add to db
            }
        });
    }
    void initToolbar(){
        initArtifactButton();
        initGraphButton();
        initViewArtifactButton();
        initSettingsButton();
    }
    private void initArtifactButton() {
        ImageButton artifactBtn = findViewById(R.id.btnAddArtifact);
        artifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddUnitForm.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initViewArtifactButton() {
        ImageButton viewArtifactBtn = findViewById(R.id.btnViewArtifact);
        viewArtifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddUnitForm.this, ViewArtifactsView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initGraphButton(){
        ImageButton btnGraph = findViewById(R.id.btnGraph);
        btnGraph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddUnitForm.this, graph_view.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initSettingsButton(){
        ImageButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddUnitForm.this, settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}