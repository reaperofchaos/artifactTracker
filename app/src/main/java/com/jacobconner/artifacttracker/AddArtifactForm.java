package com.jacobconner.artifacttracker;

import static com.jacobconner.artifacttracker.utils.FormUtils.tryParseFloat;
import static com.jacobconner.artifacttracker.utils.FormUtils.tryParseInt;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jacobconner.artifacttracker.domain.Artifact;
import com.jacobconner.artifacttracker.domain.ArtifactType;
import com.jacobconner.artifacttracker.domain.Layer;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.domain.Unit;
import com.jacobconner.artifacttracker.viewcontroller.ArtifactTypeViewModel;
import com.jacobconner.artifacttracker.viewcontroller.ArtifactViewModel;
import com.jacobconner.artifacttracker.viewcontroller.LayerViewModel;
import com.jacobconner.artifacttracker.viewcontroller.SiteViewModel;
import com.jacobconner.artifacttracker.viewcontroller.UnitViewModel;

import java.util.ArrayList;

public class AddArtifactForm extends AppCompatActivity {
    private SiteViewModel siteViewModel;
    private UnitViewModel unitViewModel;
    private LayerViewModel layerViewModel;
    private ArtifactTypeViewModel artifactTypeViewModel;
    private ArtifactViewModel artifactViewModel;
    private Spinner selectSite;
    private Spinner selectUnit;
    private Spinner selectLayer;
    private Button saveButton;
    private EditText txtArtifactId;
    private EditText txtXCoord;
    private EditText txtYCoord;
    private EditText txtZCoord;
    private Spinner selectArtifactType;
    private ArrayList<String> siteList  = new ArrayList<>();
    private ArrayList<String> unitList  = new ArrayList<>();
    private ArrayList<String> layerList  = new ArrayList<>();
    private ArrayList<String> artifactTypeList  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artifact_form);
        initToolbar();
        initForm();
        initAddButtons();
        initSaveButton();
        siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);
        unitViewModel = new ViewModelProvider(this).get(UnitViewModel.class);
        layerViewModel = new ViewModelProvider(this).get(LayerViewModel.class);
        artifactTypeViewModel = new ViewModelProvider(this).get(ArtifactTypeViewModel.class);
        artifactViewModel = new ViewModelProvider(this).get(ArtifactViewModel.class);
        ArrayAdapter<String> siteAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, siteList );
        ArrayAdapter<String> unitAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, unitList );
        ArrayAdapter<String> layerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, layerList );
        ArrayAdapter<String> artifactTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, artifactTypeList );
        try {
            selectSite.setAdapter(siteAdapter);
            siteViewModel.getSites().observe(this, sites -> {
                ArrayList<String> temp = new ArrayList<>();
                for (Site site : sites) {
                    temp.add(site.getSiteName());
                }
                siteList = temp;
                siteAdapter.addAll(temp);
                siteAdapter.notifyDataSetChanged();
            });

            selectUnit.setAdapter(unitAdapter);
            unitViewModel.getUnits().observe(this, units -> {
                ArrayList<String> temp = new ArrayList<>();
                for (Unit unit : units) {

                    temp.add(unit.getUnitName());
                }
                unitList = temp;
                unitAdapter.addAll(temp);
                unitAdapter.notifyDataSetChanged();
            });

            selectLayer.setAdapter(layerAdapter);
            layerViewModel.getLayers().observe(this, layers -> {
                ArrayList<String> temp = new ArrayList<>();
                for (Layer layer : layers) {

                    temp.add(layer.getLayerName());
                }
                unitList = temp;
                layerAdapter.addAll(temp);
                layerAdapter.notifyDataSetChanged();
            });

            selectArtifactType.setAdapter(artifactTypeAdapter);
            artifactTypeViewModel.getArtifactTypes().observe(this, artifactTypes -> {
                ArrayList<String> temp = new ArrayList<>();
                for (ArtifactType artifactType : artifactTypes) {

                    temp.add(artifactType.getArtifactType());
                }
                artifactTypeList = temp;
                artifactTypeAdapter.addAll(temp);
                artifactTypeAdapter.notifyDataSetChanged();
            });
        }catch(Exception e){
            System.out.println("an error occurred" + e);
        }
    }
    private void initForm(){
        saveButton = findViewById(R.id.btnSaveArtifact);
        selectSite = findViewById(R.id.selectSite);
        selectUnit = findViewById(R.id.selectUnit);
        selectLayer = findViewById(R.id.selectLayer);
        selectArtifactType = findViewById(R.id.selectArtifactType);
        txtArtifactId = findViewById(R.id.txtArtifactId);
        txtXCoord = findViewById(R.id.txtX);
        txtYCoord = findViewById(R.id.txtY);
        txtZCoord = findViewById(R.id.txtZ);
    }

    private void initSaveButton(){

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int artifactId = tryParseInt(txtArtifactId.getText().toString());
                float xCoord = tryParseFloat(txtXCoord.getText().toString());
                float zCoord = tryParseFloat(txtZCoord.getText().toString());
                float yCoord = tryParseFloat(txtYCoord.getText().toString());

                Artifact newArtifact = new Artifact();
                newArtifact.setId(artifactId);
                newArtifact.setxCoord(xCoord);
                newArtifact.setyCoord(yCoord);
                newArtifact.setzCoord(zCoord);
                try{
                    artifactViewModel.insert(newArtifact);
                    Toast.makeText(
                            getApplicationContext(),
                            "The artifact," + artifactId + " has been created",
                            Toast.LENGTH_LONG).show();
                }catch(Exception exception){
                    Toast.makeText(
                            getApplicationContext(),
                            "Error: The artifact was not added. \n" + exception,
                            Toast.LENGTH_LONG).show();
                }

                //clear the form
                txtYCoord.getText().clear();
                txtArtifactId.getText().clear();
                txtZCoord.getText().clear();
                txtXCoord.getText().clear();
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