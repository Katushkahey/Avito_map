package com.katushkahey.avitomap;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.katushkahey.avitomap.utils.JsonUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    Button chooseService;
    Intent intent;
    private final JsonUtils JSONUTILS = new JsonUtils();
    private List<String> names;
    private List<Pin> totalList;
    private List<String> resultList;

    private final float[] COLOURS = {BitmapDescriptorFactory.HUE_AZURE, BitmapDescriptorFactory.HUE_VIOLET, BitmapDescriptorFactory.HUE_GREEN,
            BitmapDescriptorFactory.HUE_MAGENTA, BitmapDescriptorFactory.HUE_RED};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_maps__map);
        mapFragment.getMapAsync(this);
        JSONUTILS.parsingJSON(getApplicationContext());
        totalList = JSONUTILS.getTotalList();
        names = JSONUTILS.getServices();
        chooseService = findViewById(R.id.activity_maps__button);
        chooseService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent("com.example.myapplication.ChoosingServiceActivity");
                intent.putStringArrayListExtra("names", (ArrayList<String>) names);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        } else {
            mMap.clear();
            resultList = data.getStringArrayListExtra("resultServices");
            mapFragment.getMapAsync(this);
            onMapReady(mMap);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (resultList == null || resultList.isEmpty()) {
            LatLng service = null;
            for (int i = 0; i < totalList.size(); i++) {
                double lat = totalList.get(i).getCoordinates().getLat();
                double lng = totalList.get(i).getCoordinates().getLng();
                service = new LatLng(lat, lng);
                String title = totalList.get(i).getService();
                int colourIndex = names.size();
                for (int j = 0; j < names.size(); j++) {
                    if (title.equals(names.get(j))) {
                        colourIndex = j;
                    }
                }
                if (colourIndex == names.size()) {
                    mMap.addMarker(new MarkerOptions().position(service).title(title).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                } else {
                    mMap.addMarker(new MarkerOptions().position(service).title(title).icon(BitmapDescriptorFactory.defaultMarker(COLOURS[colourIndex % 5])));
                }
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(totalList.get(totalList.size() - 1).getCoordinates().getLat(),
                    totalList.get(totalList.size() - 1).getCoordinates().getLng())));
        } else {
            for (int i = 0; i < resultList.size(); i++) {
                for (int j = 0; j < totalList.size(); j++) {
                    LatLng service = null;
                    if (resultList.get(i).equals(totalList.get(j).getService())) {
                        double lat = totalList.get(j).getCoordinates().getLat();
                        double lng = totalList.get(j).getCoordinates().getLng();
                        service = new LatLng(lat, lng);
                        String title = resultList.get(i);
                        mMap.addMarker(new MarkerOptions().position(service).title(title).icon(BitmapDescriptorFactory.defaultMarker(COLOURS[i % 5])));
                    }
                }
            }
        }
    }
}
