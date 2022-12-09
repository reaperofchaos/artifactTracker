package com.jacobconner.artifacttracker;

import static com.jacobconner.artifacttracker.utils.FormUtils.getIndex;
import static com.jacobconner.artifacttracker.utils.FormUtils.tryParseInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.jacobconner.artifacttracker.domain.Setting;
import com.jacobconner.artifacttracker.domain.Site;
import com.jacobconner.artifacttracker.viewcontroller.SettingViewModel;

import java.util.ArrayList;


public class settings extends AppCompatActivity {
    private SettingViewModel settingsViewModel;
    private ArrayList<Setting> settingList = new ArrayList<>();
    private Button btnSaveButton;
    private EditText txtServerAddress;
    private EditText txtPort;
    private EditText txtUserName;
    private EditText txtPassword;
    private Spinner spinDbType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        settingsViewModel.getSetting().observe(this, result -> {
            String connection = result.getServerAddress();
            txtServerAddress.setText(connection);
            int portNo = result.getPort();
            txtPort.setText(Integer.toString(portNo));
            String username = result.getUserName();
            txtUserName.setText(username);
            String password = result.getPassword();
            txtPassword.setText(password);
            String DbType = result.getDbType();
            int found = getIndex(spinDbType, DbType);
            if (found != -1) {
                spinDbType.setSelection(found);
            }
        });
        initForm();
        initToolbar();
        initSaveButton();
    }

    void initForm(){
        btnSaveButton = findViewById(R.id.btnSave);
        txtServerAddress = findViewById(R.id.txtServerAddress);
        txtPort = findViewById(R.id.txtPort);
        txtUserName =  findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        spinDbType = findViewById(R.id.selectDBType);
    }

    void initSaveButton(){
        btnSaveButton.setOnClickListener(view -> {
            String serverAddress = txtServerAddress.getText().toString();
            int portNo = tryParseInt(txtPort.getText().toString());
            String userName = txtUserName.getText().toString();
            String password = txtPassword.getText().toString();
            String dbType = spinDbType.getSelectedItem().toString();
            //make connection and store in db
            Setting newSetting = new Setting();
            newSetting.setServerAddress(serverAddress);
            newSetting.setPort(portNo);
            newSetting.setUserName(userName);
            newSetting.setPassword(password);
            newSetting.setDbType(dbType);

            //Create contact
            try{
                settingsViewModel.insert(newSetting);
                Toast.makeText(
                        getApplicationContext(),
                        "The settings have been saved",
                        Toast.LENGTH_LONG).show();
            }catch(Exception exception){
                Toast.makeText(
                        getApplicationContext(),
                        "Error: The settings was not saved. \n" + exception,
                        Toast.LENGTH_LONG).show();
            }

            //clear the form
            txtServerAddress.getText().clear();
            txtPort.getText().clear();
            txtUserName.getText().clear();
            txtPassword.getText().clear();
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if (settingList != null && settingList.size() > 0) {
            System.out.println("Setting is not null");
            try {
                String connection = settingList.get(0).getServerAddress();
                System.out.println("connection " + connection);
                txtServerAddress.setText(connection);
                int portNo = settingList.get(0).getPort();
                System.out.println("port " + portNo);

                txtPort.setText(Integer.toString(portNo));
                String username = settingList.get(0).getUserName();
                System.out.println("username " + username);
                txtUserName.setText(username);
                String password = settingList.get(0).getPassword();
                System.out.println("password " + password);

                txtPassword.setText(password);
                String DbType = settingList.get(0).getDbType();
                System.out.println("DbType " + DbType);
                int found = getIndex(spinDbType, DbType);
                if (found != -1) {
                    spinDbType.setSelection(found);
                }
            } catch (Exception exception) {
                System.out.println("something went wrong " + exception);
            }
        }else{
            System.out.println("Settings are null");
        }
    }

    void initToolbar(){
        initArtifactButton();
        initGraphButton();
        initViewArtifactButton();
        initSettingsButton();
    }

    private void initArtifactButton() {
        ImageButton artifactBtn = findViewById(R.id.btnAddArtifact);
        artifactBtn.setOnClickListener(view -> {
            Intent intent = new Intent(settings.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initViewArtifactButton() {
        ImageButton viewArtifactBtn = findViewById(R.id.btnViewArtifact);
        viewArtifactBtn.setOnClickListener(view -> {
            Intent intent = new Intent(settings.this, ViewArtifactsView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private  void initGraphButton(){
        ImageButton btnGraph = findViewById(R.id.btnGraph);
        btnGraph.setOnClickListener(view -> {
            Intent intent = new Intent(settings.this, graph_view.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private  void initSettingsButton(){
        ImageButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(view -> {
            Intent intent = new Intent(settings.this, settings.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}