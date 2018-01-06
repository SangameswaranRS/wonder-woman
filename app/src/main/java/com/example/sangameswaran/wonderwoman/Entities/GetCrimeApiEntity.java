package com.example.sangameswaran.wonderwoman.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Sangameswaran on 07-01-2018.
 */

public class GetCrimeApiEntity {
    String statusCode;
    List<CrimeEntity> message;

    public GetCrimeApiEntity(String statusCode, List<CrimeEntity> message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<CrimeEntity> getMessage() {
        return message;
    }

    public void setMessage(List<CrimeEntity> message) {
        this.message = message;
    }

    public interface WonderWomanRestClientInterface{
        public void onDataSubmit(GetCrimeApiEntity entity, VolleyError error);
    }
}
