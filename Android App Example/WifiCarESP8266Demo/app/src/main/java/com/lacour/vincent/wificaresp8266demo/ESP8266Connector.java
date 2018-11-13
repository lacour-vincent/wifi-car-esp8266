package com.lacour.vincent.wificaresp8266demo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ESP8266Connector {

    private Context ctx;
    private String root;
    private RequestQueue mRequestQueue;

    private final String KEY_FORWARD = "F";
    private final String KEY_BACKWARD = "B";
    private final String KEY_RIGHT = "R";
    private final String KEY_LEFT = "L";
    private final String KEY_STOP = "S";

    public ESP8266Connector(Context context, String ip, String port){
        this.ctx = context;
        this.root  = "http://" + ip + ":" + port;
    }

    public void moveForward(){
        sendRequest(KEY_FORWARD);
    }

    public void moveBackward(){
        sendRequest(KEY_BACKWARD);
    }

    public void turnRight(){
        sendRequest(KEY_RIGHT);
    }

    public void turnLeft(){
        sendRequest(KEY_LEFT);
    }

    public void stopMoving(){
        sendRequest(KEY_STOP);
    }
    
    private void sendRequest(String stateValue) {
        String request = this.root + "/?State=" + stateValue;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("send Request Response",response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("send Request Error",error.toString());
            }
        });
        stringRequest.setShouldCache(false);
        getRequestQueue().add(stringRequest);
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(ctx);
        }
        return mRequestQueue;
    }

    public void clearRequestQueue(){
        getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

}