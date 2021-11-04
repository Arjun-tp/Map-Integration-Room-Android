package com.example.test2map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test2map.EntityClass.LocationModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.test2map.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private List<LocationModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Pulling all details from DB
        list = new ArrayList<>();
        list = DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
    }

    // Menu Call
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_settings){
            Intent intent = new Intent(MapsActivity.this, UpdateActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        list = DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
        mMap = googleMap;
        // Getting all Markers
        for (int i = 0; i < list.size(); i++){
            double lat = Double.parseDouble(list.get(i).getLat());
            double lng = Double.parseDouble(list.get(i).getLng());
            LatLng coordinates = new LatLng(lat, lng);
            // Add a marker in Sydney and move the camera

            mMap.addMarker(new MarkerOptions().position(coordinates).title(list.get(i).getTitle()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
        }

        // Marker Onclick
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (int j = 0; j < list.size(); j++){
                    if (marker.getTitle().equals(list.get(j).getTitle())){
                        Intent intent = new Intent(MapsActivity.this, UpdateActivity.class);
                        intent.putExtra("titleSent", list.get(j).getTitle());
                        intent.putExtra("subTitleSent", list.get(j).getSubTitle());
                        intent.putExtra("latSent", list.get(j).getLat());
                        intent.putExtra("lngSent", list.get(j).getLng());
                        intent.putExtra("locationIdSent", list.get(j).getKey());
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }
}