package com.example.sangameswaran.wonderwoman.Entities;

/**
 * Created by Sangameswaran on 07-01-2018.
 */

public class CrimeEntity {
    String crimeId,prediction;

    public CrimeEntity(String crimeId, String prediction) {
        this.crimeId = crimeId;
        this.prediction = prediction;
    }

    public CrimeEntity() {
    }

    public String getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
