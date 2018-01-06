package com.example.sangameswaran.wonderwoman.Entities;

import com.android.volley.VolleyError;
import com.google.android.gms.location.LocationListener;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class LocationEntity {
    double latitude,longitude;

    public LocationEntity(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationEntity() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public interface WonderWomanRestClientInterface{
        public void onSubmitCoordinates(LocationEntity locationEntity, VolleyError error);
    }
}
