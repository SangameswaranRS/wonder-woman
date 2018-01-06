package com.example.sangameswaran.wonderwoman.RestCalls;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class JsonArrayBaseRequest extends JsonArrayRequest {
    public JsonArrayBaseRequest(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                10000, 3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public JsonArrayBaseRequest(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener, int timeOut, int retries) {
        super(url, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                timeOut, retries,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }

}
