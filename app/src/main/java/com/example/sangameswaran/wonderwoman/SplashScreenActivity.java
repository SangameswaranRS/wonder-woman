package com.example.sangameswaran.wonderwoman;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.sangameswaran.wonderwoman.Entities.ErrorEntity;
import com.example.sangameswaran.wonderwoman.RestCalls.RestClientImplementation;
import com.example.sangameswaran.wonderwoman.Services.LocatorService;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class SplashScreenActivity extends AppCompatActivity {
    AlertDialog.Builder permissionChecker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        askRequiredPermissionsForApplication();
        startLocatorService();
        RestClientImplementation.getCopNumberApi(new ErrorEntity(), new ErrorEntity.WonderWomanRestClientInterface() {
            @Override
            public void onGetCopDetail(ErrorEntity errorEntity, VolleyError error) {
              if(error==null){
                  SharedPreferences sp=getSharedPreferences("dataFlag",MODE_PRIVATE);
                  if(sp.getString("flag","Err").equals("set")){
                      Intent intent=new Intent(SplashScreenActivity.this,MainActivity.class);
                      startActivity(intent);
                  }else{
                      Intent intent=new Intent(SplashScreenActivity.this,RegisterActivity.class);
                      startActivity(intent);
                  }
              }else{
                  finishAffinity();
              }
            }
        },this);
    }

    public boolean askRequiredPermissionsForApplication()
    {
        Dexter.withActivity(this).withPermissions(android.Manifest.permission.READ_PHONE_STATE,android.Manifest.permission.CALL_PHONE,android.Manifest.permission.SEND_SMS).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if(report.areAllPermissionsGranted()){
                    //Toast.makeText(getApplicationContext(),"All Permissions Granted",Toast.LENGTH_LONG).show();
                }
                else {
                    permissionChecker=new AlertDialog.Builder(SplashScreenActivity.this);
                    permissionChecker.setTitle("Permission check Error").setMessage("Enable All permissions to use application").setCancelable(false).setPositiveButton("CLOSE APP", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SplashScreenActivity.this.finishAffinity();
                        }
                    }).show();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError error) {
                Toast.makeText(getApplicationContext(),"DexterError",Toast.LENGTH_LONG).show();
            }
        }).onSameThread().check();
        return true;
    }

    public void startLocatorService(){
        Intent intent=new Intent(this, LocatorService.class);
        startService(intent);
    }

}
