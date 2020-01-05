package com.example.telehealth;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


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
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new SupportInfoWindow(this));
        // Setting a custom info window adapter for the google map
        // Add a marker in Sydney and move the camera

        LatLng jenin = new LatLng(32.463393, 35.292391);
        MarkerOptions je=new MarkerOptions().position(jenin).title("AlGhad For Autism")
                .snippet("Manager:Jumana Daraghmeh"+"\n"+"Fax:042504166");
        mMap.addMarker(je);

        MarkerOptions je2=new MarkerOptions().position(new LatLng(32.462862, 35.292496))
                .title("Al Amal and AlHuda Schools")
                .snippet("Manager:Jenin Education Directorate"+"\n"+"Fax:042503503");
        mMap.addMarker(je2);

        MarkerOptions je3=new MarkerOptions().position(new LatLng(32.465696, 35.298375))
                .title("Jenin Society")
                .snippet("Manager:Amer Rahhal"+"\n"+"Fax:042439353");
        mMap.addMarker(je3);

        MarkerOptions je4=new MarkerOptions().position(new LatLng(32.459230, 35.306564))
                .title("abu aisha center")
                .snippet(""+"\n"+"Fax:042439353");
        mMap.addMarker(je4);

        MarkerOptions nab=new MarkerOptions().position(new LatLng(32.224236, 35.266219))
                .title("Care Association for Children with Needs")
                .snippet("Manager:sarab mlahes"+"\n"+"Fax:0092386097");
        mMap.addMarker(nab);

        MarkerOptions nab2=new MarkerOptions().position(new LatLng(32.221259, 35.251284))
                .title("Medical relief")
                .snippet("Manager:allam jarrar"+"\n"+"Fax:092332081");
        mMap.addMarker(nab2);

        MarkerOptions tul=new MarkerOptions().position(new LatLng(32.321207, 35.023824))
                .title("Autism Children Association")
                .snippet("Manager:hanen meleh"+"\n"+"Fax:092688958");
        mMap.addMarker(tul);

        MarkerOptions jar=new MarkerOptions().position(new LatLng(31.762153, 35.223427))
                .title("alaml school")
                .snippet("Manager:yahya alnabulsi"+"\n"+"Fax:025400722");
        mMap.addMarker(jar);

        MarkerOptions ram=new MarkerOptions().position(new LatLng(31.893185, 35.206550))
                .title("Rawan society")
                .snippet("Manager:salam asia"+"\n"+"Fax:026274449");
        mMap.addMarker(ram);

        MarkerOptions sel=new MarkerOptions().position(new LatLng(32.079150, 35.177353))
                .title("hada haqqie")
                .snippet("Manager:kefah shaheen"+"\n"+"");
        mMap.addMarker(sel);

        MarkerOptions bet=new MarkerOptions().position(new LatLng(31.698354, 35.199712))
                .title("Bethlehem Association for Rehabilitation")
                .snippet("Manager:admond shehada"+"\n"+"Fax:022744053");
        mMap.addMarker(bet);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(jenin));
    }
}
