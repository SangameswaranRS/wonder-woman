package com.example.sangameswaran.wonderwoman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.sangameswaran.wonderwoman.Constants.CommonFunctions;
import com.example.sangameswaran.wonderwoman.Entities.AbsoluteUserEntity;
import com.example.sangameswaran.wonderwoman.Entities.UserEntity;
import com.example.sangameswaran.wonderwoman.RestCalls.RestClientImplementation;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class RegisterActivity extends AppCompatActivity{
    EditText etUserName,etUserEmailID,etEmergencyContact1,etEmergencyContact2,etAddress,etAge;
    Button submitButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUserName=(EditText) findViewById(R.id.etUserName);
        etUserEmailID=(EditText)findViewById(R.id.etUserEmailID);
        etEmergencyContact1=(EditText)findViewById(R.id.etEmergencyContact1);
        etEmergencyContact2=(EditText)findViewById(R.id.etEmergencyContact2);
        etAddress=(EditText)findViewById(R.id.etAddress);
        etAge=(EditText)findViewById(R.id.etAge);
        submitButton=(Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String s1,s2,s3,s4,s5,s6;
                s1=etUserName.getText().toString();
                s2=etUserEmailID.getText().toString();
                s3=etEmergencyContact1.getText().toString();
                s4=etEmergencyContact2.getText().toString();
                s5=etAddress.getText().toString();
                s6=etAge.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")){
                    CommonFunctions.toastString("Enter All the details",RegisterActivity.this);
                }else{
                    UserEntity entity=new UserEntity(s1,s2,s3,s4,s5,s6);
                    RestClientImplementation.createUserApi(entity, new UserEntity.WonderWomanRestClientInterface() {
                        @Override
                        public void onInfoSubmit(AbsoluteUserEntity entity, VolleyError error) {
                          if(error==null){
                              SharedPreferences sp=getSharedPreferences("userData",MODE_PRIVATE);
                              SharedPreferences.Editor editor=sp.edit();
                              editor.putString("userId",entity.getUserId());
                              editor.commit();
                              SharedPreferences sp1=getSharedPreferences("dataFlag",MODE_PRIVATE);
                              SharedPreferences.Editor editor1=sp1.edit();
                              editor1.putString("flag","set");
                              editor1.commit();
                              SharedPreferences sp2=getSharedPreferences("contactInfo",MODE_PRIVATE);
                              SharedPreferences.Editor editor2=sp2.edit();
                              editor2.putString("ec1",s3);
                              editor2.putString("ec2",s4);
                              editor2.commit();
                              Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                              startActivity(intent);
                          }
                        }
                    },RegisterActivity.this);
                }
            }
        });
    }
}
