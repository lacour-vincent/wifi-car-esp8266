package com.lacour.vincent.wificaresp8266demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    Button buttonForward = null;
    Button buttonBackward = null;
    Button buttonLeft = null;
    Button buttonRight = null;

    ESP8266Connector esp8266Connector;
    String IP = "192.168.4.1";
    String port = "80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonForward = findViewById(R.id.buttonForward);
        buttonBackward = findViewById(R.id.buttonBackward);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);

        buttonForward.setOnTouchListener(this);
        buttonBackward.setOnTouchListener(this);
        buttonLeft.setOnTouchListener(this);
        buttonRight.setOnTouchListener(this);

        esp8266Connector = new ESP8266Connector(this,this.IP,this.port);
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.buttonForward:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonForward.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    esp8266Connector.moveForward();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonForward.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    esp8266Connector.stopMoving();
                }
                break;
            case R.id.buttonBackward:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonBackward.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    esp8266Connector.moveBackward();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonBackward.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    esp8266Connector.stopMoving();
                }
                break;
            case R.id.buttonRight:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonRight.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    esp8266Connector.turnRight();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonRight.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    esp8266Connector.stopMoving();
                }
                break;
            case R.id.buttonLeft:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonLeft.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    esp8266Connector.turnLeft();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonLeft.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    esp8266Connector.stopMoving();
                }
                break;
        }
        return true;
    }

    @Override
    public void onStop() {
        esp8266Connector.stopMoving();
        esp8266Connector.clearRequestQueue();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        esp8266Connector.stopMoving();
        esp8266Connector.clearRequestQueue();
        super.onDestroy();
    }

}