package com.example.user.tracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Sender extends AppCompatActivity {
    Button button;
    TextView textView;
    LocationManager locationManager;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);
        button = (Button) findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Global.longitude = location.getLongitude();
                Global.lattitude = location.getLatitude();
                textView.append("\n"+location.getLongitude()+"  "+location.getLatitude());

               if(Global.lattitude != null) {
                   Send senderlog = new Send(Sender.this);
                   senderlog.razmil();
               }
            }
                @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 10); // TODO: Consider calling}
            else {
                locationManager.requestLocationUpdates("gps", 5000, 5, locationListener);
                configurebutton();


            }
        }

    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configurebutton();
                return;
        }

    }
    private void configurebutton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if ((ActivityCompat.checkSelfPermission(Sender.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(Sender.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

                        requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 10); // TODO: Consider calling}
                    }
                    else{
                        locationManager.requestLocationUpdates("gps", 5000, 5, locationListener);
                        return;
                    }

                }
            }

        });

    }

}
