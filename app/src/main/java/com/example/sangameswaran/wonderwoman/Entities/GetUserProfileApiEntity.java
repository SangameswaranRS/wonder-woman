package com.example.sangameswaran.wonderwoman.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Sangameswaran on 07-01-2018.
 */

public class GetUserProfileApiEntity {
    String statusCode;
    List<AbsoluteUserEntity> message;

    public GetUserProfileApiEntity(String statusCode, List<AbsoluteUserEntity> message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public GetUserProfileApiEntity() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<AbsoluteUserEntity> getMessage() {
        return message;
    }

    public void setMessage(List<AbsoluteUserEntity> message) {
        this.message = message;
    }

}
