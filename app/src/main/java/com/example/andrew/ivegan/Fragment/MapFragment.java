package com.example.andrew.ivegan.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.andrew.ivegan.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MapFragment extends Fragment {
    MapView mMapView;
    private GoogleMap googleMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap mMap) {
                googleMap = mMap;

                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        if (location != null) {
                            googleMap.clear();
                            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(userLocation).title("Your position"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f));
                        }
                        setData(mMap);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                };

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                                    android.Manifest.permission.ACCESS_COARSE_LOCATION},
                            1);

                } else {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2, 2f, locationListener);
                    Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(userLastLocation)
                            .title("Marker in SPB"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation, 15f));


                    //59.979815, 30.298685
                    //59.980394, 30.282935
                    //59.976142, 30.288385
                    //59.974552, 30.283621

                    //59.979726, 30.297483

                    // For showing a move to my location button
                    //googleMap.setMyLocationEnabled(true);

                    // For dropping a marker at a point on the Map
                    //LatLng sydney = new LatLng(-34, 151);
                    //googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                    // For zooming automatically to the location of the marker
                    //CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                    //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            }
        });

        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        200, 2f, locationListener);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void setData(GoogleMap mMap) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(59.979815, 30.298685))
                .title("Marker in SPB"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(59.980394, 30.282935))
                .title("Marker in SPB"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(59.976142, 30.288385))
                .title("Marker in SPB"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(59.974552, 30.283621))
                .title("Marker in SPB"));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}