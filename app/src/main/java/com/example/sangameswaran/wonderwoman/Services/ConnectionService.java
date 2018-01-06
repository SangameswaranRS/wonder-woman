package com.example.sangameswaran.wonderwoman.Services;

import android.Manifest;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.sangameswaran.wonderwoman.Constants.CommonFunctions;
import com.example.sangameswaran.wonderwoman.Entities.CrimeEntity;
import com.example.sangameswaran.wonderwoman.Entities.GetCrimeApiEntity;
import com.example.sangameswaran.wonderwoman.Entities.LocationEntity;
import com.example.sangameswaran.wonderwoman.Entities.PostCrimeEntity;
import com.example.sangameswaran.wonderwoman.RestCalls.RestClientImplementation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.Executor;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class ConnectionService extends Service implements LocationListener{
    BluetoothSocket mSocket;
    BluetoothDevice mDevice;
    InputStream mInput;
    byte[] mBuffer;
    UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    int readBytes=0;
    private FusedLocationProviderClient mFusedLocationClient;
    SmsManager sms;

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
        if(intent.getExtras()!=null){
            mBuffer=new byte[100];
            mDevice=intent.getExtras().getParcelable("mDevice");
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            sms=SmsManager.getDefault();
            if(mDevice==null){
                Toast.makeText(this,"Something went wrong..Try again",Toast.LENGTH_LONG).show();
            }else {
                try {
                    mSocket=mDevice.createRfcommSocketToServiceRecord(MY_UUID);
                    Log.d("Tag","Socket Created without Error");
                    mSocket.connect();
                    Log.d("Tag","Connected to server");
                    Toast.makeText(this,"ALL FINE",Toast.LENGTH_LONG).show();
                    final Handler keepGettingIps=new Handler();
                    keepGettingIps.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mInput=mSocket.getInputStream();
                                readBytes=mInput.read(mBuffer);
                                Log.d("SERVICE","Bytes read : "+readBytes);
                                String s=new String(mBuffer);
                                Toast.makeText(getApplicationContext(),"Recieved :"+s,Toast.LENGTH_LONG).show();
                                if (ActivityCompat.checkSelfPermission(ConnectionService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ConnectionService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                mFusedLocationClient.getLastLocation()
                                        .addOnSuccessListener(new Executor() {
                                            @Override
                                            public void execute(@NonNull Runnable runnable) {
                                                runnable.run();
                                            }
                                        }, new OnSuccessListener<Location>() {
                                            @Override
                                            public void onSuccess(final Location location) {
                                                if (location != null) {
                                                    SharedPreferences sp2=getSharedPreferences("contactInfo",MODE_PRIVATE);
                                                    String ec1=sp2.getString("ec1","");
                                                    String ec2=sp2.getString("ec2","");
                                                    sms.sendTextMessage(ec1,null,"Danger Alert Detected Coordinates : Latitude : "+location.getLatitude()+",Longitude : "+location.getLongitude(),null,null);
                                                    sms.sendTextMessage(ec2,null,"Danger Alert Detected Coordinates : Latitude : "+location.getLatitude()+",Longitude : "+location.getLongitude(),null,null);
                                                    RestClientImplementation.postCrimeApi(new LocationEntity(location.getLatitude(), location.getLongitude()), new LocationEntity.WonderWomanRestClientInterface() {
                                                        @Override
                                                        public void onSubmitCoordinates(LocationEntity locationEntity, VolleyError error) {
                                                            if(error == null){
                                                                //Error reported..Call Prediction Api
                                                                SharedPreferences sharedPreferences=getSharedPreferences("crimeId",MODE_PRIVATE);
                                                                String crimeId=sharedPreferences.getString("crimeId",null);
                                                                final PostCrimeEntity e=new PostCrimeEntity(crimeId);
                                                                RestClientImplementation.getPredictionApi(e, new PostCrimeEntity.WonderWomanRestClientInterface() {
                                                                    @Override
                                                                    public void onInfoSubmit(GetCrimeApiEntity entity, VolleyError error) {
                                                                        if(error==null) {
                                                                            CrimeEntity entity1 = entity.getMessage().get(0);
                                                                            String prediction = entity1.getPrediction();
                                                                            sms.sendTextMessage("9965226501", null, "Danger Alert Detected Coordinates : Latitude : " + location.getLatitude() + ",Longitude : " + location.getLongitude() + "Predicted Crime : " + prediction, null, null);
                                                                        }
                                                                    }
                                                                },ConnectionService.this);

                                                            }else{
                                                                CommonFunctions.toastString("Retrying..",ConnectionService.this);
                                                            }
                                                        }
                                                    },ConnectionService.this);
                                                }
                                            }
                                        });
                                keepGettingIps.postDelayed(this,5000);

                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                                stopSelf();
                            }
                        }
                    },2000);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    stopSelf();
                }
            }
        }else{
            Toast.makeText(this,"Something went wrong..Try again",Toast.LENGTH_LONG).show();
            stopSelf();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
