package com.example.sangameswaran.wonderwoman.Services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.android.volley.VolleyError;
import com.example.sangameswaran.wonderwoman.Entities.LocationEntity;
import com.example.sangameswaran.wonderwoman.RestCalls.RestClientImplementation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.concurrent.Executor;

/**
 * Created by Sangameswaran on 07-01-2018.
 */

public class LocatorService extends Service {

    private FusedLocationProviderClient mFusedLocationClient;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        final Handler looper = new Handler();
        looper.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ActivityCompat.checkSelfPermission(LocatorService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocatorService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mFusedLocationClient.getLastLocation().addOnSuccessListener(new Executor() {
                    @Override
                    public void execute(@NonNull Runnable runnable) {
                            runnable.run();
                    }
                }, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null){
                            LocationEntity locationEntity=new LocationEntity(location.getLatitude(),location.getLongitude());
                            RestClientImplementation.obtainLocationQuery(locationEntity, new LocationEntity.WonderWomanRestClientInterface() {
                                @Override
                                public void onSubmitCoordinates(LocationEntity locationEntity, VolleyError error) {
                                    if(error==null){
                                        //callBack
                                    }
                                }
                            },LocatorService.this);
                        }
                    }
                });

                looper.postDelayed(this,50000);
            }
        },100);
        return super.onStartCommand(intent, flags, startId);
    }
}
