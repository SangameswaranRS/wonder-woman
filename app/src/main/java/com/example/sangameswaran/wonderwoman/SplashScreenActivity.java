package com.example.sangameswaran.wonderwoman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.sangameswaran.wonderwoman.Entities.ErrorEntity;
import com.example.sangameswaran.wonderwoman.RestCalls.RestClientImplementation;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
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
}
