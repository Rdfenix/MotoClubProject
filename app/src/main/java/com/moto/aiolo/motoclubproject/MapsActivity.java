package com.moto.aiolo.motoclubproject;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moto.aiolo.motoclubproject.Interface.EventOperation;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.EventResponse;
import com.moto.aiolo.motoclubproject.Mthods.APIMethod;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapsActivity extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener {


    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private final int REQUEST_ACCESS_FINE_LOCATION = 1;
    private final int REQUEST_ACCESS_COARSE_LOCATION = 2;

    //variavel de log
    private static final String TAG = "conexao";
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.activity_maps, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.mMap = googleMap;
        permissionLocalizeSolicited(REQUEST_ACCESS_FINE_LOCATION);
        permissionLocalizeSolicited(REQUEST_ACCESS_COARSE_LOCATION);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mMap.clear();
        setMyLocation();
    }

    private void setMyLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null){
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            float zoom = 15;

            LatLng latLng = new LatLng(latitude, longitude);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
            mMap.animateCamera(cameraUpdate);
            configureMaps();
        }
    }

    public void addMakers(){
        Retrofit retrofit = APIMethod.API();
        EventOperation eventOperation = retrofit.create(EventOperation.class);
        Call<List<EventResponse>> request = eventOperation.getAllEvents();

        request.enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<EventResponse>> call, @NonNull Response<List<EventResponse>> response) {
                if (!response.isSuccessful()){
                    Log.e("ERROR ", response.message());
                } else {

                    try {
                        List<EventResponse> points = response.body();
                        for (int i = 0; i < points.size(); i++){
                            Double lat = points.get(i).getLat();
                            Double lon = points.get(i).getLon();
                            String title = points.get(i).getTitle();
                            MarkerOptions markerOptions = new MarkerOptions();
                            LatLng latLng = new LatLng(lat, lon);
                            markerOptions.position(latLng);
                            markerOptions.title(title);
                            mMap.addMarker(markerOptions);
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                        }
                    } catch (Exception e){
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<EventResponse>> call, @NonNull Throwable t) {

            }
        });
    }

    private void configureMaps() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        addMakers();
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

        marker.setPosition(marker.getPosition());

    }

    private void permissionLocalizeSolicited(int permissionType){
        int permissionCheck;

        switch (permissionType){
            case REQUEST_ACCESS_FINE_LOCATION:
                permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){
                        showExplanation("É necessário a permissão de acesso ao GPS", "Atenção", Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_ACCESS_FINE_LOCATION);
                    } else {
                        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_ACCESS_FINE_LOCATION);
                    }
                }else{
                    mMap.setMyLocationEnabled(true);
                    Toast.makeText(getContext(), "Permissão de GPS já concedida", Toast.LENGTH_SHORT).show();
                }
            case REQUEST_ACCESS_COARSE_LOCATION:
                permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)){
                        showExplanation("É necessário a permissão de acesso à localização", "Atenção", Manifest.permission.ACCESS_COARSE_LOCATION, REQUEST_ACCESS_COARSE_LOCATION);
                    } else {
                        requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION, REQUEST_ACCESS_COARSE_LOCATION);
                    }
                } else {
                    mMap.setMyLocationEnabled(true);
                    Toast.makeText(getContext(), "Permissão de acesso à localização já concedida", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showExplanation(String title, String message, final String permission, final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{permissionName}, permissionRequestCode);
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}

