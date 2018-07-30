package com.example.archek.geo2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends ListActivity implements OnMapReadyCallback {

    private static final int REQUEST_LOCATION =1;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private GoogleApiClient mGoogleApiClient;

    String plaQue;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);// TODO Call map and toolbar

    }
 
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_activity_main, menu );
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id) {
            case R.id.gmap1:
                return true;
            case R.id.listItem:
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);// TODO Call second activity from toolbar
                break;


            default:
                return super.onOptionsItemSelected( item );

        }

        return false;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getSystemService( getBaseContext().LOCATION_SERVICE );
        if (!locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
            buildAlertMessageNoGps();
        } else if (locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
            getLocation();// TODO Generate markers
        }
        mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
        // Add a marker in Sydney and move the camera
        LatLng north = new LatLng( 62, 41 );
        LatLng unknown = new LatLng( 0, 0 );
        LatLng russia = new LatLng( 54, 39 );
        Marker etName = mMap.addMarker( new MarkerOptions().position( north ).title( "Marker in north" ).draggable( false ) );
        mMap.addMarker( new MarkerOptions().position( unknown ).title( "Marker in 0" ).draggable( true ) );
        mMap.addMarker( new MarkerOptions().position( russia ).title( "Marker in Russia" ).draggable( true ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLng( unknown ) );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.addMarker( new MarkerOptions().position( unknown ).title( "Marker in 0" ) );
        mMap.addMarker( new MarkerOptions().position( russia ).title( "Marker in Russia" ) );
        mMap.addMarker( new MarkerOptions().position( north ).title( "Marker in north" ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLng( russia ) );
        mMap.setMyLocationEnabled( true );
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {// TODO Button add marker
                double latTi = 44;
                double lonGi = 44;
                LatLng poseMark= new LatLng(latTi,lonGi);
                Marker markerNew;
                markerNew = mMap.addMarker(new MarkerOptions().position(poseMark).title("****").draggable(true));
            }
        });
    }
    private void buildAlertMessageNoGps() {
    }

    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (MapsActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        }else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);// TODO Call marker with self location
            mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Current location").draggable(true));
            }}



}