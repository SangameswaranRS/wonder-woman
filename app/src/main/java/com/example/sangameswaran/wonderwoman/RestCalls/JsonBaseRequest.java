package com.example.sangameswaran.wonderwoman.RestCalls;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class JsonBaseRequest extends JsonObjectRequest {
    public JsonBaseRequest(int method, String url, JSONObject jsonObj, Response.Listener<JSONObject> listener,
                           Response.ErrorListener errorListener) {
        super(method, url, jsonObj, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                10000, 1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public JsonBaseRequest(int method, String url, JSONObject jsonObj, Response.Listener<JSONObject> listener,
                           Response.ErrorListener errorListener, int timeOut, int retries) {
        super(method, url, jsonObj, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                timeOut, retries,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }

}

