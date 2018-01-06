package com.example.sangameswaran.wonderwoman.Entities;

import com.android.volley.VolleyError;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class ErrorEntity {
    String statusCode,message;

    public ErrorEntity(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorEntity() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public interface WonderWomanRestClientInterface{
        public void onGetCopDetail(ErrorEntity errorEntity, VolleyError error);
    }
}
