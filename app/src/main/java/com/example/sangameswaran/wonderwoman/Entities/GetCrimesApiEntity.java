package com.example.sangameswaran.wonderwoman.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Sangameswaran on 07-01-2018.
 */

public class GetCrimesApiEntity {
    String statusCode;
    List<CrimeReportEntity> message;

    public GetCrimesApiEntity(String statusCode, List<CrimeReportEntity> message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public GetCrimesApiEntity() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<CrimeReportEntity> getMessage() {
        return message;
    }

    public void setMessage(List<CrimeReportEntity> message) {
        this.message = message;
    }

    public interface WonderWomanRestClientInterface{
        public void onGetAllInfo(GetCrimesApiEntity entity, VolleyError error);
    }
}
