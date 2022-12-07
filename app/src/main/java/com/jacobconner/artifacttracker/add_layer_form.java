package com.jacobconner.artifacttracker;

import static com.jacobconner.artifacttracker.utils.FormUtils.tryParseFloat;
import static com.jacobconner.artifacttracker.utils.FormUtils.tryParseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jacobconner.artifacttracker.domain.Layer;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.domain.Unit;
import com.jacobconner.artifacttracker.viewcontroller.LayerViewModel;
import com.jacobconner.artifacttracker.viewcontroller.SiteViewModel;
import com.jacobconner.artifacttracker.viewcontroller.UnitViewModel;

import java.util.ArrayList;

public class add_layer_form extends AppCompatActivity {
    private SiteViewModel siteViewModel;
    private UnitViewModel unitViewModel;
    private LayerViewModel layerViewModel;
    private Spinner selectSite;
    private Spinner selectUnit;
    private EditText txtLayerName;
    private EditText txtLayerId;
    private Button saveButton;
    private ArrayList<String> siteList  = new ArrayList<>();
    private ArrayList<String> unitList  = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_layer_form);
        initToolbar();
        initForm();
        initAddUnitButton();
        initAddSiteButton();
        initSaveButton();
        siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);
        unitViewModel = new ViewModelProvider(this).get(UnitViewModel.class);
        layerViewModel = new ViewModelProvider(this).get(LayerViewModel.class);
        ArrayAdapter<String> siteAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, siteList );
        ArrayAdapter<String> unitAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, unitList );

        selectSite.setAdapter(siteAdapter);
        siteViewModel.getSites().observe(this, sites -> {
            ArrayList<String> temp = new ArrayList<>();
            for(Site site: sites){

                temp.add(site.getSiteName());
            }
            siteList = temp;
            siteAdapter.addAll(temp);
            siteAdapter.notifyDataSetChanged();
        });

        selectUnit.setAdapter(unitAdapter);
        unitViewModel.getUnits().observe(this, units -> {
            ArrayList<String> temp = new ArrayList<>();
            for(Unit unit: units){

                temp.add(unit.getUnitName());
            }
            unitList = temp;
            unitAdapter.addAll(temp);
            unitAdapter.notifyDataSetChanged();
        });

    }
    private void initForm(){
        saveButton = findViewById(R.id.btnSaveLayer);
        selectSite = findViewById(R.id.selectSiteName);
        selectUnit = findViewById(R.id.selectUnit);
        txtLayerName = findViewById(R.id.txtLayerName);
        txtLayerId = findViewById(R.id.txtLayerId);
    }

    private void initSaveButton() {

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               //create a layer and add to database
                String layerName = txtLayerName.getText().toString();
                int layerId = tryParseInt(txtLayerId.getText().toString());

                Layer newLayer = new Layer();
                newLayer.setId(layerId);
                newLayer.setLayerName(layerName);
                    //Create contact
                    try{
                        layerViewModel.insert(newLayer);
                        Toast.makeText(
                                getApplicationContext(),
                                "The layer," + layerName + " has been created",
                                Toast.LENGTH_LONG).show();
                    }catch(Exception exception){
                        Toast.makeText(
                                getApplicationContext(),
                                "Error: The layer was not added. \n" + exception,
                                Toast.LENGTH_LONG).show();
                    }

                    //clear the form
                    txtLayerId.getText().clear();
                    txtLayerName.getText().clear();
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