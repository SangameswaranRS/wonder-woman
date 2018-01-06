package com.example.sangameswaran.wonderwoman.Entities;

/**
 * Created by Sangameswaran on 07-01-2018.
 */

public class CrimeReportEntity {
    String crimeId,latitude,longitude,crimeTimeInEpoch;

    public CrimeReportEntity(String crimeId, String latitude, String longitude, String crimeTimeInEpoch) {
        this.crimeId = crimeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.crimeTimeInEpoch = crimeTimeInEpoch;
    }

    public CrimeReportEntity() {
    }

    public String getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(String crimeId) {
        this.crimeId = crimeId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCrimeTimeInEpoch() {
        return crimeTimeInEpoch;
    }

    public void setCrimeTimeInEpoch(String crimeTimeInEpoch) {
        this.crimeTimeInEpoch = crimeTimeInEpoch;
    }
}
