package com.jacobconner.artifacttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.jacobconner.artifacttracker.domain.ArtifactType;
import com.jacobconner.artifacttracker.viewcontroller.ArtifactTypeViewModel;

public class AddArtifactType extends AppCompatActivity {
    private ArtifactTypeViewModel artifactTypeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artifact_type);
        artifactTypeViewModel = new ViewModelProvider(this).get(ArtifactTypeViewModel.class);

        initToolbar();
        initSaveButton();
    }

    void initSaveButton(){
        Button saveButton = findViewById(R.id.btnSaveArtifactType);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                EditText txtArtifactType = findViewById(R.id.txtArtifactType);
                String artifactTypeName = txtArtifactType.getText().toString();
                boolean nameIsEmpty = txtArtifactType.getText().toString().trim().length() == 0;

                ArtifactType newType = new ArtifactType();
                newType.setArtifactType(artifactTypeName);


                if(!nameIsEmpty){
                    //Create contact
                    try{
                        artifactTypeViewModel.insert(newType);
                        Toast.makeText(
                                getApplicationContext(),
                                "The type," + artifactTypeName + " has been created",
                                Toast.LENGTH_LONG).show();
                    }catch(Exception exception){
                        Toast.makeText(
                                getApplicationContext(),
                                "Error: The artifact type " + artifactTypeName + " was not added. \n" + exception,
                                Toast.LENGTH_LONG).show();
                    }

                    //clear the form
                    txtArtifactType.getText().clear();

                }else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Could not submit. Please type a name for the artifact type.",
                            Toast.LENGTH_LONG).show();
                }
                //create an ArtifactType object and add to database
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
                Intent intent = new Intent(AddArtifactType.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initViewArtifactButton() {
        ImageButton viewArtifactBtn = findViewById(R.id.btnViewArtifact);
        viewArtifactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactType.this, ViewArtifactsView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initGraphButton(){
        ImageButton btnGraph = findViewById(R.id.btnGraph);
        btnGraph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactType.this, graph_view.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private  void initSettingsButton(){
        ImageButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddArtifactType.this, settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}