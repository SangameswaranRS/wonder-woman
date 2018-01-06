package com.example.sangameswaran.wonderwoman.Entities;

import com.android.volley.VolleyError;

/**
 * Created by Sangameswaran on 07-01-2018.
 */

public class PostCrimeEntity {
    String crimeId;

    public PostCrimeEntity(String crimeId) {
        this.crimeId = crimeId;
    }

    public String getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }

    public interface WonderWomanRestClientInterface{
        public void onInfoSubmit(GetCrimeApiEntity entity, VolleyError error);
    }
}
