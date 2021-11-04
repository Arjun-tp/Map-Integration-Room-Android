package com.example.test2map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test2map.EntityClass.LocationModel;

public class UpdateActivity extends AppCompatActivity {

    EditText title, subTitle, latitude, longitude;
    Button save, back, delete, update;
    String title_txt = "", subTitle_txt = "", lat_txt = "", lng_txt = "", locationTitle, locationSubTitle,locationLat, locationLng, locationKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Initialization
        title = findViewById(R.id.extTitle);
        subTitle = findViewById(R.id.extSubTitle);
        latitude = findViewById(R.id.extLatitude);
        longitude = findViewById(R.id.extLongitude);
        save = findViewById(R.id.btnSave);
        back = findViewById(R.id.btnBack);
        delete = findViewById(R.id.btnDelete);
        update = findViewById(R.id.btnUpdate);

        delete.setVisibility(View.INVISIBLE);
        update.setVisibility(View.INVISIBLE);

        locationTitle = getIntent().getStringExtra("titleSent");
        locationSubTitle = getIntent().getStringExtra("subTitleSent");
        locationLat = getIntent().getStringExtra("latSent");
        locationLng = getIntent().getStringExtra("lngSent");

        title.setText(locationTitle);
        subTitle.setText(locationSubTitle);
        latitude.setText(locationLat);
        longitude.setText(locationLng);

        title_txt = title.getText().toString().trim();
        subTitle_txt = subTitle.getText().toString().trim();
        lat_txt = latitude.getText().toString().trim();
        lng_txt = longitude.getText().toString().trim();

        // Save and Update Button Visibility Check
        if (!title_txt.equals("")){
            save.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
        }

        // Save Button Function
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveData();
            }
        });

        // Back Button Function
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
            }
        });

        // Delete Button Function
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = getIntent().getExtras().getInt("locationIdSent");
                DatabaseClass.getDatabase(getApplicationContext()).getDao().deleteData(id);
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
            }
        });

        // Update/Edit Button Function
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
    }

    // Update
    public void updateData(){
        int id = getIntent().getExtras().getInt("locationIdSent");
        DatabaseClass.getDatabase(getApplicationContext()).getDao().updateData(title.getText().toString().trim(), subTitle.getText().toString().trim(), latitude.getText().toString().trim(), longitude.getText().toString().trim(), id);
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
        DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
        Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
    }

    // Save
    public void saveData(){
        title_txt = title.getText().toString().trim();
        subTitle_txt = subTitle.getText().toString().trim();
        lat_txt = latitude.getText().toString().trim();
        lng_txt = longitude.getText().toString().trim();

        LocationModel model = new LocationModel();
        model.setTitle(title_txt);
        model.setSubTitle(subTitle_txt);
        model.setLat(lat_txt);
        model.setLng(lng_txt);

        DatabaseClass.getDatabase(getApplicationContext()).getDao().insertAllData(model);
        title.setText("");
        subTitle.setText("");
        latitude.setText("");
        longitude.setText("");
        Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
    }

}