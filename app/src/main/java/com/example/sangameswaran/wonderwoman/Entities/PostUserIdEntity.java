package com.example.sangameswaran.wonderwoman.Entities;

import com.android.volley.VolleyError;

/**
 * Created by Sangameswaran on 07-01-2018.
 */

public class PostUserIdEntity {
    String userId;

    public PostUserIdEntity(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public interface WonderWomanRestClientInterface{
        public void getInfo(GetUserProfileApiEntity entity, VolleyError error);
    }
}
