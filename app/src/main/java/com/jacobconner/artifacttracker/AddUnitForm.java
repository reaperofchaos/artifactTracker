package com.jacobconner.artifacttracker;

import static com.jacobconner.artifacttracker.utils.FormUtils.tryParseFloat;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.domain.Unit;
import com.jacobconner.artifacttracker.viewcontroller.SiteViewModel;
import com.jacobconner.artifacttracker.viewcontroller.UnitViewModel;
import java.util.ArrayList;

public class AddUnitForm extends AppCompatActivity {
    private SiteViewModel siteViewModel;
    private UnitViewModel unitViewModel;
    private ArrayList<String> siteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit_form);
        siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);
        unitViewModel = new ViewModelProvider(this).get(UnitViewModel.class);
        Spinner siteSpinner = findViewById(R.id.selectSiteName);
        siteList = new ArrayList<>();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, siteList );
        siteSpinner.setAdapter(dataAdapter);
        siteViewModel.getSites().observe(this, sites -> {
            ArrayList<String> temp = new ArrayList<>();
            for(Site site: sites){

                temp.add(site.getSiteName());
            }
            siteList = temp;
            dataAdapter.addAll(temp);
            dataAdapter.notifyDataSetChanged();
        });

        initToolbar();
        initAddSiteButton();
        initAdddUnitButton();
    }

    private void initAddSiteButton() {
        ImageButton artifactBtn = findViewById(R.id.btnAddSite);
        artifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddUnitForm.this, AddSiteForm.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAdddUnitButton(){
        Button addUnitButton = findViewById(R.id.btnSaveUnit);
        EditText txtUnitName = findViewById(R.id.txtUnitName);
        EditText txtUnitWidth = findViewById(R.id.txtUnitWidth);
        EditText txtUnitHeight = findViewById(R.id.txtUnitHeight);
        Spinner spinHeightUnits = findViewById(R.id.selectHeightUnits);
        Spinner spinWidthUnits = findViewById(R.id.selectWidthUnits);
        Spinner spinSiteName = findViewById(R.id.selectSiteName);

        addUnitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String unitName = txtUnitName.getText().toString();
                Float unitWidth = tryParseFloat(txtUnitWidth.getText().toString());
                Float unitHeight = tryParseFloat(txtUnitHeight.getText().toString());
                String heightUnits = spinHeightUnits.getSelectedItem().toString();
                String widthUnits = spinWidthUnits.getSelectedItem().toString();
                String siteName = spinSiteName.getSelectedItem().toString();
                boolean nameIsEmpty = txtUnitName.getText().toString().trim().length() == 0;

                //build a unit and add to db
                Unit newUnit = new Unit();
                newUnit.setUnitName(unitName);
                newUnit.setUnitHeight(unitHeight);
                newUnit.setUnitWidth(unitWidth);
                newUnit.setHeightUnits(heightUnits);
                newUnit.setWidthUnits(widthUnits);
                if(!nameIsEmpty){
                    //Create contact
                    try{
                        unitViewModel.insert(newUnit);
                        Toast.makeText(
                                getApplicationContext(),
                                "The unit," + unitName + " has been created",
                                Toast.LENGTH_LONG).show();
                    }catch(Exception exception){
                        Toast.makeText(
                                getApplicationContext(),
                                "Error: The unit was not added. \n" + exception,
                                Toast.LENGTH_LONG).show();
                    }

                    //clear the form
                    txtUnitName.getText().clear();
                    txtUnitWidth.getText().clear();
                    txtUnitHeight.getText().clear();
                }else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Could not submit. Please type a name for your site.",
                            Toast.LENGTH_LONG).show();
                }
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