package com.example.sangameswaran.wonderwoman.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.sangameswaran.wonderwoman.Entities.GetUserProfileApiEntity;
import com.example.sangameswaran.wonderwoman.Entities.PostUserIdEntity;
import com.example.sangameswaran.wonderwoman.R;
import com.example.sangameswaran.wonderwoman.RestCalls.RestClientImplementation;

/**
 * Created by Sangameswaran on 07-01-2018.
 */

public class MyProfileFragment extends Fragment {
    TextView tvUserName,tvUserEmailId,tvec1,tvec2,tvAge,tvUserId,tvAddress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.my_profile_fragment,container,false);
        tvUserName=(TextView)v.findViewById(R.id.tvUserName);
        tvUserEmailId=(TextView)v.findViewById(R.id.tvUserEmailId);
        tvec1=(TextView) v.findViewById(R.id.tvec1);
        tvec2=(TextView)v.findViewById(R.id.tvec2);
        tvAddress= (TextView) v.findViewById(R.id.tvAddress);
        tvAge=(TextView) v.findViewById(R.id.tvAge);
        tvUserId=(TextView)v.findViewById(R.id.tvUserId);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String userId=sharedPreferences.getString("userId",null);
        RestClientImplementation.getUserProfile(new PostUserIdEntity(userId), new PostUserIdEntity.WonderWomanRestClientInterface() {
            @Override
            public void getInfo(GetUserProfileApiEntity entity, VolleyError error) {
                if(error==null){
                    tvUserName.setText("User name : "+entity.getMessage().get(0).getUserName());
                    tvUserEmailId.setText("User Email Id : "+entity.getMessage().get(0).getUserEmailId());
                    tvec1.setText("Emergency Contact 1 : "+entity.getMessage().get(0).getEmergencyContact1());
                    tvec2.setText("Emergency Contact 2 : "+entity.getMessage().get(0).getEmergencyContact2());
                    tvAddress.setText("Address : " + entity.getMessage().get(0).getUserHomeAddress());
                    tvAge.setText("Age : "+entity.getMessage().get(0).getUserAge());
                    tvUserId.setText("User Id : "+entity.getMessage().get(0).getUserId());
                }
            }
        },getActivity());
        return v;
    }
}
