package com.example.sangameswaran.wonderwoman.RestCalls;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.sangameswaran.wonderwoman.Constants.CommonFunctions;
import com.example.sangameswaran.wonderwoman.Constants.Constants;
import com.example.sangameswaran.wonderwoman.Entities.AbsoluteUserEntity;
import com.example.sangameswaran.wonderwoman.Entities.ErrorEntity;
import com.example.sangameswaran.wonderwoman.Entities.UserEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class RestClientImplementation {
    static RequestQueue queue;
    public static String getAbsoluteURL(String relativeURL){
        return Constants.BASE_URL+relativeURL;
    }
    public static void createUserApi(UserEntity entity, final UserEntity.WonderWomanRestClientInterface restClientInterface, final Context context){
        queue= VolleySingleton.getInstance(context).getRequestQueue();
        String API_URL=getAbsoluteURL("/createUser");
        final Gson gson=new Gson();
        String jsonString = gson.toJson(entity, UserEntity.class);
        try {
            JSONObject postParam=new JSONObject(jsonString);
            JsonBaseRequest postRequest=new JsonBaseRequest(Request.Method.POST, API_URL, postParam, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String userId=response.getString("userId");
                        AbsoluteUserEntity userEntity=new AbsoluteUserEntity();
                        userEntity.setUserId(userId);
                        String message= response.getString("message");
                        CommonFunctions.toastString(message,context);
                        restClientInterface.onInfoSubmit(userEntity,null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        restClientInterface.onInfoSubmit(null,new VolleyError());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        if(error.networkResponse!=null){
                            ErrorEntity errorEntity;
                            errorEntity = gson.fromJson(new String(error.networkResponse.data), ErrorEntity.class);
                            CommonFunctions.toastString(errorEntity.getMessage(),context);
                        }
                        else {
                            CommonFunctions.toastString(error.getMessage(),context);
                        }
                    }catch (Exception e){
                        CommonFunctions.toastString(e.getMessage(),context);
                    }
                }
            });
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getCopNumberApi(final ErrorEntity errorEntity, final ErrorEntity.WonderWomanRestClientInterface restClientInterface, final Context context){
        String API_URL=getAbsoluteURL("/getCopNumber");
        queue=VolleySingleton.getInstance(context).getRequestQueue();
        final Gson gson=new Gson();
        final JsonBaseRequest getRequest=new JsonBaseRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String copNumber=response.getString("message");
                    SharedPreferences sp=context.getSharedPreferences("copNumber",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("copNumber",copNumber);
                    editor.commit();
                    ErrorEntity newErr=gson.fromJson(response.toString(),ErrorEntity.class);
                    //CommonFunctions.toastString(newErr.getMessage(),context);
                    restClientInterface.onGetCopDetail(newErr,null);
                } catch (JSONException e) {
                    CommonFunctions.toastString("Something went wrong!",context);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CommonFunctions.toastString("Something went wrong!",context);
            }
        });
        queue.add(getRequest);
    }
}