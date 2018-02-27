package com.example.user.gps_service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;



public class Gpshelper extends Service {
private LocationListener locationListener;
    private LocationManager locationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Global.lattitude=location.getLatitude();
                Global.longitude=location.getLongitude();
                Send send=new Send(getApplicationContext());
                send.razmil();
                Toast.makeText(Gpshelper.this,""+Global.longitude + Global.lattitude,Toast.LENGTH_LONG).show();
                Intent i=new Intent("location_update");
                i.putExtra("coordinates",location.getLatitude()+""+location.getLongitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };
        locationManager=(LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,5,locationListener);

        return START_STICKY;

    }

    @Override
    public void onCreate() {
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
             Global.longitude=location.getLongitude();
                Global.lattitude=location.getLatitude();
                //Toast.makeText(Gpshelper.this,""+Global.lattitude+ Global.longitude,Toast.LENGTH_SHORT ).show();

                Send send=new Send(getApplicationContext());
                send.razmil();
                Intent i=new Intent("location_update");
                i.putExtra("coordinates",location.getLatitude()+""+location.getLongitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
Intent i=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };
        locationManager=(LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,5,locationListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager!=null){
            locationManager.removeUpdates(locationListener);
        }
    }
}
