package com.example.samplecodedump;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.example.samplecodedump.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final LatLng LOCATION_TAIPEI = new LatLng(25.0330, 121.5654);
    private final LatLng LOCATION_VANCOUVER = new LatLng(49.2827, -123.1207);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Sample 17: Map markers + camera movement
        sampleSeventeenMapMovementMarkers();
    }

    private void sampleSeventeenMapMovementMarkers() {
        Button sampleSeventeenTaipei = findViewById(R.id.btnSampleSeventeenTaipei);
        sampleSeventeenTaipei.setOnClickListener(view -> {
            mMap.addMarker(new MarkerOptions().position(LOCATION_TAIPEI).title("Taipei"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_TAIPEI, 12));
        });

        Button sampleSeventeenVancouver = findViewById(R.id.btnSampleSeventeenVancouver);
        sampleSeventeenVancouver.setOnClickListener(view -> {
            mMap.addMarker(new MarkerOptions().position(LOCATION_VANCOUVER).title("Vancouver"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_VANCOUVER, 11));
        });
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MapsActivity.class);
    }
}