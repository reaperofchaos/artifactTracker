package com.jacobconner.artifacttracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jacobconner.artifacttracker.databinding.ActivityAddSiteFormBinding;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.viewcontroller.SiteViewModel;

public class AddSiteForm extends AppCompatActivity {
    private SiteViewModel siteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site_form);
        siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);

        initToolbar();
        initSaveButton();
    }

    public static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public static float tryParseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
            return 0.0F;
        }
    }

    private void initSaveButton() {
        Button saveBtn = findViewById(R.id.btnSaveSite);
        EditText txtSiteName = findViewById(R.id.txtSiteName);
        EditText txtSiteId = findViewById(R.id.txtSiteId);
        EditText txtLatitude = findViewById(R.id.txtLatitude);
        EditText txtLongitude = findViewById(R.id.txtLongitude);



        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String siteName = txtSiteName.getText().toString();
                int siteId = tryParseInt(txtSiteId.getText().toString());
                float latitude = tryParseFloat(txtLatitude.getText().toString());
                float longitude = tryParseFloat(txtLongitude.getText().toString());
                boolean nameIsEmpty = txtSiteName.getText().toString().trim().length() == 0;

                Site newSite = new Site();
                newSite.setId(siteId);
                newSite.setSiteName(siteName);
                newSite.setLatitude(latitude);
                newSite.setLongitude(longitude);

                if(!nameIsEmpty){
                    //Create contact
                    try{
                        siteViewModel.insert(newSite);
                        Toast.makeText(
                                getApplicationContext(),
                                "The site," + siteName + " has been created",
                                Toast.LENGTH_LONG).show();
                    }catch(Exception exception){
                        Toast.makeText(
                                getApplicationContext(),
                                "Error: The site was not added. \n" + exception,
                                Toast.LENGTH_LONG).show();
                    }

                    //clear the form
                    txtSiteName.getText().clear();
                    txtSiteId.getText().clear();
                    txtLatitude.getText().clear();
                    txtLongitude.getText().clear();
                }else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Could not submit. Please type a name for your site.",
                            Toast.LENGTH_LONG).show();
                }
              //create a site and add to database
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
                Intent intent = new Intent(AddSiteForm.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initViewArtifactButton() {
        ImageButton viewArtifactBtn = findViewById(R.id.btnViewArtifact);
        viewArtifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddSiteForm.this, ViewArtifactsView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initGraphButton(){
        ImageButton btnGraph = findViewById(R.id.btnGraph);
        btnGraph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddSiteForm.this, graph_view.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initSettingsButton(){
        ImageButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddSiteForm.this, settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}